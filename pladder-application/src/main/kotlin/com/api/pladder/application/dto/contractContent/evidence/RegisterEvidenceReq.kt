package com.api.pladder.application.dto.contractContent.evidence

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class RegisterEvidenceReq (
    @Schema(description = "계약서 ID")
    val contractId: String,
    @Schema(description = "제목")
    val title : String,
    @Schema(description = "첨부파일")
    val file : List<MultipartFile>
)