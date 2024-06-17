package com.api.pladder.application.service.file

import com.api.pladder.application.dto.file.mapper.FileDtoMapper
import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.application.dto.file.response.FileResp
import com.api.pladder.application.dto.file.response.FileTestResp
import com.api.pladder.application.service.file.manager.FileManager
import com.api.pladder.application.service.file.reader.FileReader
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.file.FileUtils
import com.api.pladder.core.utils.s3.ImageS3Provider
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.entity.file.enums.FileExtension
import com.api.pladder.domain.entity.file.enums.FileType
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
        fileValidation(req)
        val fileName = generateFileName(req)
        val model = FileDtoMapper.toEntity(req)

        val result = manager.save(model)
        // save image-file
        s3Provider.upload(fileName = fileName, req.file)

        return result
    }

    private fun fileValidation(req: FileReq) {
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


    fun convertToType(type : String): FileType {
        return FileType.fromPrefix(type)

    }

    fun deleteById(id: String, authUserObject: AuthUserObject) {
        val model = reader.findById(id)
        if ((authUserObject.userType == com.api.pladder.core.enums.UserType.CUSTOMER) && (model.targetId != authUserObject.userId)){
            throw AccessDeniedException("해당 이미지를 삭제할 권한이 없습니다.")
        }
        s3Provider.delete(id)
        manager.deleteById(id)
    }

    fun findById(id: String, authUserObject: AuthUserObject): FileResp {
        val model = reader.findById(id)
        if (model.targetId != authUserObject.userId){
            throw AccessDeniedException("해당 이미지를 조회할 권한이 없습니다.")
        }
        return FileResp(model)
    }

    fun getImage(companyId : UUID): FileTestResp {
        val imageObj = reader.findByTargetId(companyId)
        val byteArray = s3Provider.download(imageObj.fileName)
        return FileTestResp(imageObj,byteArray)
    }

}

