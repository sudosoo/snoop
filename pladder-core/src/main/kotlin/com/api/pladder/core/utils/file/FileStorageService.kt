package com.api.pladder.core.utils.file

import org.springframework.web.multipart.MultipartFile

interface FileStorageService {
    fun upload(fileName: String, fileRequest: MultipartFile)
    fun download(fileName: String): ByteArray
    fun delete(fileName: String)

}