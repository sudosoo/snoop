package com.api.pladder.application.service.file

import com.api.pladder.application.dto.file.mapper.FileDtoMapper
import com.api.pladder.application.dto.file.request.FileReq
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
    fun save(req: FileReq
    ): File {
        validation(req)
        val fileName = generateFileName(req)
        req.fileName = fileName
        val model = FileDtoMapper.toEntity(req)

        val result = manager.save(model)
        // save image-file
        s3Provider.upload(fileName = fileName, req.file)

        return result
    }

    private fun validation(req: FileReq) {
        val fileExtension = fileUtils.getExtension(req.file.originalFilename!!)
        if (!FileExtension.hasExtension(fileExtension.lowercase())) {
            throw IllegalArgumentException("Unsupported file extension: $fileExtension")
        } else if (req.file.size > maxFileSize.toBytes()) {
            throw IllegalArgumentException("File size exceeds the maximum size: ${req.file.size}")
        }
    }

    private fun generateFileName(request: FileReq) : String{
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

    fun findByTargetIdAndTargetType(targetId: UUID, targetType: FileTargetType): List<FileResp> {
        val files = reader.findByTargetIdAndType(targetId, targetType)
        return files.map {
            val byteArray = s3Provider.downloadByFileName(it.fileName)
            FileResp(
                fileName = it.fileName,
                byteArray = byteArray
            )
        }
    }

}

