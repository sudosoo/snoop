package com.api.pladder.application.dto.progressHistory.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile



class ProgressContentRegisterReq (
    @Schema(description="계약서 ID")
    val contractId: String,
    @Schema(description="계약서 내용")
    val content: String,
    @Schema(description = "이미지 종류" ,
        example = "PR = 프로필 , " +
                "CL = 회사로고 , " +
                "ID = 신분증 , " +
                "LR = 지자체 신고증 , " +
                "DL = 탐정 면허증 , " +
                "BU = 사업자 등록증" +
                "ED = 증거사진")
    val type: String,
    @Schema(description="증거자료 첨부")
    val files: MultipartFile? = null
)