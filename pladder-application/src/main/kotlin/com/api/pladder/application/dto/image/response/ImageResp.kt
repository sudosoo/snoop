package com.api.pladder.application.dto.image.response

import com.api.pladder.domain.entity.image.Image
import io.swagger.v3.oas.annotations.media.Schema

data class ImageResp(
    @Schema(description = "이미지 ID")
    val imageId : String?,

    @Schema(description = "이미지 주소")
    val location : String? = "${imageId}"
){
    constructor(model: Image) : this(
        imageId = model.imageId,
        location = "${model.imageId}"
    )
}