package com.api.pladder.application.dto.company.request

import com.api.pladder.domain.entity.user.enums.Specialty
import io.swagger.v3.oas.annotations.media.Schema

class UpdateCompanyInfoReq (
    @Schema(description="회사 ID")
    val companyId : String,
    @Schema(description="소개" , example = "사기전문 탐정 홍길동 사무소 입니다.")
    val introduction : String,
    @Schema(description="분야 목록 : 최대 3개" , example = "[\"FRAUD\",\"VIOLENCE\",\"SEXUAL_HARASSMENT\"]")
    val specialization : List<Specialty> = emptyList()
)
