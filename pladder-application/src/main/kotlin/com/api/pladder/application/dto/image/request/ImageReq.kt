package com.api.pladder.application.dto.image.request

import com.api.pladder.domain.entity.image.enums.ImageType
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class ImageReq(
    @Schema(description = "이미지 종류" , example = "PB")
    val type : ImageType,
    @Schema(description = "파일")
    val file: MultipartFile,
    val fileSize: Long
){
    constructor(type: String, file: MultipartFile) : this(
        type = ImageType.fromPrefix(type.uppercase()),
        file = file,
        fileSize = file.size
    )

}