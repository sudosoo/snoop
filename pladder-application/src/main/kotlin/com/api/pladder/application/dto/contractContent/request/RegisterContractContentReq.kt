package com.api.pladder.application.dto.contractContent.request

import com.api.pladder.domain.entity.user.enums.Filed
import io.swagger.v3.oas.annotations.media.Schema

class RegisterContractContentReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="내용")
    val content : String,
    @Schema(description="분야")
    val contractField : Filed,
    @Schema(description="사건 장소")
    var incidentLocation: String? = null,
    @Schema(description="사건 시간")
    var incidentTime : String ?= null
){
}