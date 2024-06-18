package com.api.pladder.application.dto.user.detective.request

import io.swagger.v3.oas.annotations.media.Schema

class RegisterDetectiveCareerReq (
    @Schema(description="재직 기간 :" +
            "2020.01 ~ 2021.01" +
            "")
    val period : String,
    @Schema(description="설명 :"+
            "경찰청에서 근무했던 경력이 있습니다.")
    val description : String
)