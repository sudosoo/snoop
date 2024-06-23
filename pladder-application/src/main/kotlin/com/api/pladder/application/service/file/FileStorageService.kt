package com.api.pladder.application.service.file

import com.api.pladder.application.dto.file.request.FileRequest

interface FileStorageService {
    fun uploadImage(fileName: String, fileRequest: FileRequest)
    fun downloadImage(fileName: String): ByteArray
    fun deleteImage(fileName: String)

}