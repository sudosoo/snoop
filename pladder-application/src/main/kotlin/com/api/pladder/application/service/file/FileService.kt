package com.api.pladder.application.service.file

import com.api.pladder.application.dto.file.mapper.FileDtoMapper
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.dto.file.response.FileResp
import com.api.pladder.application.service.file.manager.FileManager
import com.api.pladder.application.service.file.reader.FileReader
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.utils.file.FileUtils
import com.api.pladder.core.utils.s3.ImageS3Provider
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.entity.file.enums.FileExtension
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimeConvert.convertToString
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimePattern
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.unit.DataSize
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*

@Service
class FileService(
    private var s3Provider: ImageS3Provider,
    private val reader: FileReader,
    private val manager : FileManager,
    private val fileUtils: FileUtils,

    @Value("\${multipart.max-upload-size}")
    private var maxFileSize: DataSize
){
    @Transactional
    fun save(request: FileRequest
    ): File {
        validation(request)
        val fileName = generateName(request)
        request.fileName = fileName
        val model = FileDtoMapper.toEntity(request)

        val result = manager.save(model)
        s3Provider.upload(fileName = fileName, request.file)

        return result
    }

    private fun validation(request: FileRequest) {
        val fileExtension = fileUtils.getExtension(request.file.originalFilename!!)
        if (!FileExtension.hasExtension(fileExtension.lowercase())) {
            throw IllegalArgumentException("Unsupported file extension: $fileExtension")
        } else if (request.file.size > maxFileSize.toBytes()) {
            throw IllegalArgumentException("File size exceeds the maximum size: ${request.file.size}")
        }
    }

    private fun generateName(request: FileRequest) : String{
        val timestamp = convertToString(LocalDateTime.now(), DateTimePattern.COMPACT)
        val userRole = when (request.userType) {
            UserType.CUSTOMER -> "CU"
            UserType.DETECTIVE -> "DE"
            UserType.ADMIN -> "SU"
            else -> throw IllegalArgumentException("Unsupported user type: ${request.userType}")
        }
        val extension = fileUtils.getExtension(request.file.originalFilename!!)
        val filePrefix = fileUtils.getCategory(extension)

        return "${filePrefix}${userRole}${request.type.prefix}${timestamp}.${extension}"
    }

    @Transactional
    fun deleteById(fileName: String) {
        s3Provider.delete(fileName)
        manager.deleteById(fileName)
    }

    @Transactional(readOnly = true)
    fun getPagedFileRespByTargetIdAndTargetType(
        targetId: UUID,
        targetType: FileTargetType,
        pageRequest: PageRequest
    ): List<FileResp> {
        val files = reader.findByTargetIdAndTargetType(targetId, targetType, pageRequest)
        return files.content.map {
            val byteArray = s3Provider.downloadByFileName(it.fileName)
            FileResp(
                fileName = it.fileName,
                byteArray = byteArray,
            )
        }
    }

    @Transactional
    fun updateProfileImage(request: FileRequest) {
        reader.findByTargetIdAndTargetTypeAndFileType(request.targetId, request.targetType, request.type).ifPresentOrElse(
            //있으면 삭제 후 새로운 이미지 저장
            { image ->
                deleteById(image.fileName)
                save(request)
            },
            { save(request) }
        )
    }

    fun getProfileImage(targetId: UUID, targetType: FileTargetType): FileResp {
        val file = reader.findByTargetIdAndTargetTypeAndFileType(targetId, targetType ,FileType.PROFILE)
            .orElseThrow { throw NoSuchElementException("File not found") }
        val byteArray = s3Provider.downloadByFileName(file.fileName)

        return FileResp(
            fileName = file.fileName,
            byteArray = byteArray,
        )
    }

    fun test(files: List<MultipartFile>){
        val req = FileRequest(
            type = FileType.PROFILE,
            file = files[0],
            targetId = UUID.randomUUID(),
            targetType = FileTargetType.CONTRACT,
            writerId = UUID.randomUUID(),
            userType = UserType.CUSTOMER
        )
        save(req)
    }

}

