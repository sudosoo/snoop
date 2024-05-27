package com.api.pladder.application.dto.image.response

import com.api.pladder.domain.entity.image.Image

//@Schema(name = "Image-Response")
data class ImageResp(
    //@Schema(description = "이미지 ID")
    val imageId : String?,

    //@Schema(description = "이미지 주소")
    val location : String? = "/open/images/${imageId}"
){
    constructor(model: Image) : this(
        imageId = model.id,
        location = "/open/images/${model.id}"
    )
}