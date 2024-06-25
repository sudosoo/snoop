package com.api.pladder.application.dto.company.request

import com.api.pladder.domain.entity.user.enums.Specialty
import io.swagger.v3.oas.annotations.media.Schema

class UpdateCompanyInfoReq (
    @Schema(description="회사 ID" , example = "0a554452-da4a-4427-a20b-b8a5fa63800e")
    val companyId : String,
    @Schema(description="소개" , example = "사기전문 탐정 홍길동 사무소 입니다.")
    val introduction : String,
    @Schema(description="분야 목록 : 최대 3개" , example = "[\"FRAUD\",\"VIOLENCE\",\"SEXUAL_HARASSMENT\"]")
    val specialization : List<Specialty> = emptyList()
)
