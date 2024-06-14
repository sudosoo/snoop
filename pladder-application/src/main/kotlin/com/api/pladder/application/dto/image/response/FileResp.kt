package com.api.pladder.application.dto.image.response

import com.api.pladder.domain.entity.image.File
import io.swagger.v3.oas.annotations.media.Schema

data class FileResp(
    @Schema(description = "이미지 ID")
    val imageId : String?,

    @Schema(description = "이미지 주소")
    val location : String? = "${imageId}"
){
    constructor(model: File) : this(
        imageId = model.imageId,
        location = "${model.imageId}"
    )
}