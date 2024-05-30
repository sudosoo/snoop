package com.api.pladder.application.service.image

import com.api.pladder.application.core.exception.AccessDeniedException
import com.api.pladder.application.dto.auth.request.AuthReq
import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.application.dto.image.response.ImageResp
import com.api.pladder.application.service.image.manager.ImageManager
import com.api.pladder.application.service.image.reader.ImageReader
import com.api.pladder.application.service.image.s3.ImageS3ServiceImpl
import com.api.pladder.domain.entity.image.Image
import com.api.pladder.domain.entity.user.enums.UserType
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimeConvert.convertToString
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimePattern
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.unit.DataSize
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class ImageService(
    private var s3Service: ImageS3ServiceImpl,
    private val reader: ImageReader,
    private val manager : ImageManager,

    @Value("\${multipart.max-upload-size}")
    private var maxFileSize: DataSize
){
    fun save(req: ImageReq ,
             //authReq: AuthReq
    ): ImageResp {

        fileValidation(req)

        // file-name 채번
        val fileName = generateImageFileName(req)
        //val writerId = authReq.userId.toString()
        //TODO : writerId 추가
        val model = Image.of(fileName,"testUser" ,req.type, req.file.size)

        val result = manager.save(model)
        // save image-file
        s3Service.uploadImage(fileName = fileName, imageReq = req)

        return ImageResp(result)
    }

    private fun fileValidation(req: ImageReq) {
        val fileExtension = getFileExtension(req.file.originalFilename)
        if (!req.type.extension.contains(fileExtension.lowercase())) {
            throw IllegalArgumentException("Unsupported file extension: $fileExtension")
        } else if (req.fileSize > maxFileSize.toBytes()) {
            throw IllegalArgumentException("File size exceeds the maximum size: ${req.file.size}")
        }
    }

    private fun getFileExtension(filename: String?): String {
        return filename?.substringAfterLast('.', "") ?: ""
    }


    private fun generateImageFileName(request: ImageReq) : String{
        val timestamp = convertToString(LocalDateTime.now(), DateTimePattern.COMPACT)
        val random = String.format("%02d", Random.nextInt(0, 100))
        return "IM${request.type.prefix}${timestamp}${random}"
    }

    fun deleteById(id: String, authReq: AuthReq) {
        val model = reader.findById(id)
        if (authReq.userType == UserType.CUSTOMER && model.writerId != authReq.userId.toString()){
            throw AccessDeniedException("해당 이미지를 삭제할 권한이 없습니다.")
        }
        s3Service.deleteImage(id)
        manager.deleteById(id)
    }

    fun findById(id: String, authReq: AuthReq ): ImageResp {
        val model = reader.findById(id)
        if (authReq.userType == UserType.CUSTOMER && model.writerId != authReq.userId.toString()){
            throw AccessDeniedException("해당 이미지를 조회할 권한이 없습니다.")
        }
        return ImageResp(model)
    }

    fun downloadImage(fileName: String): ByteArray {
        return s3Service.downloadImage(fileName)
    }
}