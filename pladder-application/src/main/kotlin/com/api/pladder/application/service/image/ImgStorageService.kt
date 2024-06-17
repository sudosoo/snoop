package com.api.pladder.application.service.image

import com.api.pladder.application.dto.file.request.FileReq

interface ImgStorageService {
    fun uploadImage(fileName: String, fileReq: FileReq)
    fun downloadImage(fileName: String): ByteArray
    fun deleteImage(fileName: String)

}