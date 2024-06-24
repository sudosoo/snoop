package com.api.pladder.application.dto.contract.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile


class RegisterSignReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="사인 이미지")
    val image: MultipartFile
)

