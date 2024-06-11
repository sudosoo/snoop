package com.api.pladder.application.service.image

import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.application.dto.image.response.ImageResp
import com.api.pladder.application.dto.image.response.ImageTestResp
import com.api.pladder.application.service.image.manager.ImageManager
import com.api.pladder.application.service.image.reader.ImageReader
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.s3.ImageS3Provider
import com.api.pladder.domain.entity.image.Image
import com.api.pladder.domain.entity.image.enums.ImageExtension
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimeConvert.convertToString
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimePattern
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.unit.DataSize
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

@Service
class ImageService(
    private var s3Provider: ImageS3Provider,
    private val reader: ImageReader,
    private val manager : ImageManager,

    @Value("\${multipart.max-upload-size}")
    private var maxFileSize: DataSize
){
    fun save(req: ImageReq ,
             authReq: AuthUserObject
    ): ImageResp {
        fileValidation(req)
        val fileName = generateImageFileName(req)
        val model = Image.of(fileName,authReq.userId ,req.type, req.file.size)

        val result = manager.save(model)
        // save image-file
        s3Provider.uploadImage(fileName = fileName, req.file)

        return ImageResp(result)
    }

    private fun fileValidation(req: ImageReq) {
        val fileExtension = getFileExtension(req.file.originalFilename)
        if (!ImageExtension.hasExtension(fileExtension.lowercase())) {
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
        val extension = getExtensionFromMimeType(request.file.contentType!!)
        return "IM${request.type.prefix}${timestamp}${random}${extension}"
    }

    private fun getExtensionFromMimeType(mimeType: String): String {
        return when (mimeType) {
            "image/jpg" -> ".jpg"
            "image/jpeg" -> ".jpeg"
            "image/png" -> ".png"
            "image/gif" -> ".gif"
            "application/pdf" -> ".pdf"
            else -> throw IllegalArgumentException("지원하지 않는 파일 타입 입니다: $mimeType")
        }
    }

    fun deleteById(id: String, authUserObject: AuthUserObject) {
        val model = reader.findById(id)
        if ((authUserObject.userType == com.api.pladder.core.enums.UserType.CUSTOMER) && (model.companyId != authUserObject.userId)){
            throw AccessDeniedException("해당 이미지를 삭제할 권한이 없습니다.")
        }
        s3Provider.deleteImage(id)
        manager.deleteById(id)
    }

    fun findById(id: String, authUserObject: AuthUserObject): ImageResp {
        val model = reader.findById(id)
        if (model.companyId != authUserObject.userId){
            throw AccessDeniedException("해당 이미지를 조회할 권한이 없습니다.")
        }
        return ImageResp(model)
    }

    fun getImage(companyId : UUID): ImageTestResp {
        val imageObj = reader.findByWriterId(companyId)
        val byteArray = s3Provider.downloadImage(imageObj.imageId)
        return ImageTestResp(imageObj,byteArray)
    }

}

