package com.api.pladder.application.dto.file.response

import com.api.pladder.domain.entity.file.File
import io.swagger.v3.oas.annotations.media.Schema

data class FileTestResp(
    @Schema(description = "이미지 ID")
    val imageId : String,
    @Schema(description = "파일 확장자")
    val imageExtension: String,
    @Schema(description = "이미지 바이트")
    val byte: ByteArray
){
    constructor(model: File, byteArray: ByteArray) : this(
        imageId = model.fileName,
        imageExtension = model.getExtension().toString().lowercase(),
        byte = byteArray
    )
}