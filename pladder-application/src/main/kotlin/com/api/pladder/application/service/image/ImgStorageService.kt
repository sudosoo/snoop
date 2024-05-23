package com.api.pladder.application.service.image

import com.api.pladder.application.dto.image.request.ImageReq

interface ImgStorageService {
    fun uploadImage(fileName: String, imageReq: ImageReq)
    fun downloadImage(fileName: String): ByteArray
    fun deleteImage(fileName: String)

}