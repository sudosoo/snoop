package com.api.pladder.application.dto.contract.evidence

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class RegisterEvidenceReq (
    @Schema(description = "계약서 ID")
    val contractId: String,
    @Schema(description = "파일 종류" ,
        example =
                "EI = 증거 사진"+
                "EO = 증거 오디오"
    )
    var type : String,
    @Schema(description = "증거 자료 제목")
    val title :String,
    @Schema(description = "첨부파일")
    val file : MutableList<MultipartFile>
)