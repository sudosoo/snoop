package com.api.pladder.application.dto.file.response

import io.swagger.v3.oas.annotations.media.Schema

data class FileResp(
    @Schema(description = "파일 이름")
    val fileName : String,
    @Schema(description = "이미지 주소")
    val byteArray: ByteArray
)