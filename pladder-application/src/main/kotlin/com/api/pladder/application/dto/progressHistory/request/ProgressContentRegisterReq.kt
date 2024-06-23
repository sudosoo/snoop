package com.api.pladder.application.dto.progressHistory.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class ProgressContentRegisterReq (
    @Schema(description="계약서 ID")
    val contractId: String,
    @Schema(description="계약서 내용")
    val content: String,
    @Schema(description = "파일 종류" ,
        example =
                "EI = 증거 사진"+
                "EO = 증거 오디오"
    )
    val type: String,
    @Schema(description="증거 자료")
    val files: MultipartFile? = null
)