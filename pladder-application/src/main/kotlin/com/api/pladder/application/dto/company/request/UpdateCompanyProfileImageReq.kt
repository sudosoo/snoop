package com.api.pladder.application.dto.company.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class UpdateCompanyProfileImageReq (
    @Schema(description="회사 Id")
    val companyId : String,
    @Schema(description="이미지")
    val image : MultipartFile,
)
