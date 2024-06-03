package com.api.pladder.application.dto.company.request

import com.api.pladder.domain.entity.user.enums.Filed
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description="회사 등록 요청")
class RegisterCompanyReq (
    @Schema(description="회사명",example = "플래더")
    val companyName: String,
    @Schema(description="회사 전화번호",example = "010-0000-0000")
    val phoneNumber: String,
    @Schema(description="회사 주소",example = "서울시 강남구 서초동 22-1")
    val addr: String,
    @Schema(description="전문 분야",example = "[\"AFFAIR\",\"FRAUD\",\"MISSING\"]")
    val specialization: List<Filed>,
    @Schema(description="회사 소개",example = "안녕하세요 실종 전문 탐정 플래더 입니다.")
    val introduction: String
)