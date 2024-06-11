package com.api.pladder.application.dto.contractContent.victim

import io.swagger.v3.oas.annotations.media.Schema

class UpdateVictimReq (
    @Schema(description="계약서 ID")
    val contractId : String,
    @Schema(description="이름")
    val name : String,
    @Schema(description="관계")
    val relationship : String,
    @Schema(description="전화번호")
    val phoneNumber : String
)