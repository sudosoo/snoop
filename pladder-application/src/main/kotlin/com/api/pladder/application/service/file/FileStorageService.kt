package com.api.pladder.application.service.file

import com.api.pladder.application.dto.file.request.FileReq

interface FileStorageService {
    fun uploadImage(fileName: String, fileReq: FileReq)
    fun downloadImage(fileName: String): ByteArray
    fun deleteImage(fileName: String)

}