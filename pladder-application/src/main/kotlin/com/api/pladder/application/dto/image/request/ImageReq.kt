package com.api.pladder.application.dto.image.request

import org.springframework.web.multipart.MultipartFile

class ImageReq(
    val type : ImageType,
    val file: MultipartFile
){
    constructor(type: String, file: MultipartFile) : this(
        type = ImageType.valueOf(type),
        file = file
    )
}