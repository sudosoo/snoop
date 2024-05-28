package com.api.pladder.application.dto.image.request

import com.api.pladder.domain.entity.image.enums.ImageType
import org.springframework.web.multipart.MultipartFile

class ImageReq(
    val type : ImageType,
    val file: MultipartFile
){
    constructor(type: String, file: MultipartFile) : this(
        type = ImageType.fromPrefix(type.uppercase()),
        file = file
    )

}