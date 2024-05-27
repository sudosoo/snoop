package com.api.pladder.application.service.image

import com.api.pladder.application.core.exception.AccessDeniedException
import com.api.pladder.application.dto.auth.request.AuthReq
import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.application.dto.image.response.ImageResp
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.image.Image
import com.api.pladder.domain.entity.user.enums.UserType
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.ImageRepository
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimeConvert.convertToString
import com.sudosoo.takeItEasy.application.common.DateTime.DateTimePattern
import java.time.LocalDateTime
import kotlin.random.Random

class ImageService (
    private var repository: ImageRepository,
    private var imageService: ImageS3ServiceImpl
):JpaService<Image,String>{

    override var jpaRepository: BaseRepository<Image, String> = repository

    fun save(request: ImageReq , authReq: AuthReq): ImageResp {

        // file-name 채번
        val fileName = generateImageFileName(request)
        val writerId = authReq.userId.toString()
        // save image-info
        val model = Image.of(fileName,writerId,request.type,request.file.size)
        val result = repository.save(model)

        // save image-file
        imageService.uploadImage(fileName = fileName, imageReq = request)

        return ImageResp(result)
    }

    private fun generateImageFileName(request: ImageReq) : String{
        val timestamp = convertToString(LocalDateTime.now(), DateTimePattern.COMPACT)
        val random = String.format("%02d", Random.nextInt(0, 100))
        return "IM${request.type.prefix}${timestamp}${random}"
    }

    fun deleteById(id: String, authReq: AuthReq) {
        val model = findById(id)
        if (authReq.userType == UserType.CUSTOMER && model.writerId != authReq.userId.toString()){
            throw AccessDeniedException("해당 이미지를 삭제할 권한이 없습니다.")
        }

        imageService.deleteImage(id)
        deleteById(id)
    }

    // TODO : 처리방법 조회
    fun findById(id: String, authReq: AuthReq ): ImageResp {
        val model = findById(id)
        if (authReq.userType == UserType.CUSTOMER && model.writerId != authReq.userId.toString()){
            throw AccessDeniedException("해당 이미지를 조회할 권한이 없습니다.")
        }

        return ImageResp(model)
    }

    fun downloadImage(fileName: String): ByteArray {
        return imageService.downloadImage(fileName)
    }
}