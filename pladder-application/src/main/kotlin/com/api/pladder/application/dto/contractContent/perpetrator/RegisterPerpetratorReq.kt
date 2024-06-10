package com.api.pladder.application.dto.contractContent.perpetrator

import com.api.pladder.domain.entity.contract.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema

class RegisterPerpetratorReq (
    @Schema(description="계약서 ID")
    val contractId : String,
    @Schema(description="이름")
    val name : String,
    @Schema(description="성별")
    val gender : Gender,
    @Schema(description="나이")
    val age : Int,
    @Schema(description="관계")
    var relationship : String? = null,
    @Schema(description="직장 주소")
    var workplaceAddr : String? = null,
    @Schema(description="인상 착의")
    val impression : String,
    @Schema(description="주거지")
    var residenceAddr : String? = null,
    @Schema(description="공범 Id")
    var accompliceId : String? = null,
)