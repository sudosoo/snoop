package com.api.pladder.application.dto.company.request

import com.api.pladder.domain.entity.user.enums.Specialty
import io.swagger.v3.oas.annotations.media.Schema

class RegisterCompanyReq (
    @Schema(description="회사 이름" , example = "홍길동 사무소")
    val name : String,
    @Schema(description="주소" , example = "서울시 강남구")
    val addr : String,
    @Schema(description="연락처" , example = "010-1234-5678")
    val phoneNumber : String,
    @Schema(description="소개" , example = "사기전문 탐정 홍길동 사무소 입니다.")
    val introduction : String,
    @Schema(description="분야 목록 : 최대 3개" , example = "[\"FRAUD\",\"VIOLENCE\",\"SEXUAL_HARASSMENT\"]")
    val specialization : List<Specialty> = emptyList()
)