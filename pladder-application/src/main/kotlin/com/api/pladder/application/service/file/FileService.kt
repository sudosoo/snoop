package com.api.pladder.application.service.file

import com.api.pladder.application.dto.file.mapper.FileDtoMapper
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.dto.file.response.FileResp
import com.api.pladder.application.service.file.manager.FileManager
import com.api.pladder.application.service.file.reader.FileReader
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.file.FileUtils
import com.api.pladder.core.utils.s3.ImageS3Provider
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.entity.file.enums.FileExtension
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimeConvert.convertToString
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimePattern
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.util.unit.DataSize
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

@Service
class FileService(
    private var s3Provider: ImageS3Provider,
    private val reader: FileReader,
    private val manager : FileManager,
    private val fileUtils: FileUtils,

    @Value("\${multipart.max-upload-size}")
    private var maxFileSize: DataSize
){
    fun save(request: FileRequest
    ): File {
        validation(request)
        val fileName = generateFileName(request)
        request.fileName = fileName
        val model = FileDtoMapper.toEntity(request)

        val result = manager.save(model)
        // save image-file
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

    private fun generateFileName(request: FileRequest) : String{
        val timestamp = convertToString(LocalDateTime.now(), DateTimePattern.COMPACT)
        val random = String.format("%02d", Random.nextInt(0, 100))
        val extension = fileUtils.getExtension(request.file.originalFilename!!)
        val filePrefix = fileUtils.getCategory(extension)
            return "${filePrefix}${request.type.prefix}${timestamp}${random}.${extension}"
    }

    fun deleteById(id: String, authUserObject: AuthUserObject) {
        val model = reader.findById(id)
        if ((authUserObject.userType == com.api.pladder.core.enums.UserType.CUSTOMER) && (model.targetId != authUserObject.userId)){
            throw AccessDeniedException("해당 이미지를 삭제할 권한이 없습니다.")
        }
        s3Provider.delete(id)
        manager.deleteById(id)
    }

    fun findByTargetIdAndTargetType(
        targetId: UUID,
        targetType: FileTargetType,
        pageRequest: PageRequest
    ): List<FileResp> {
        val files = reader.findByTargetIdAndType(targetId, targetType,pageRequest)
        return files.content.map {
            val byteArray = s3Provider.downloadByFileName(it.fileName)
            FileResp(
                fileName = it.fileName,
                byteArray = byteArray
            )
        }
    }

}

