package com.api.pladder.application.dto.contract.request

import com.api.pladder.domain.entity.user.enums.Specialty
import io.swagger.v3.oas.annotations.media.Schema

class UpdateContractContentReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="분야")
    val contractField : Specialty,
    @Schema(description="사건 장소")
    var incidentLocation: String? = null,
    @Schema(description="사건 시간 yyyy-MM-dd HH:mm")
    var incidentTime : String ?= null
){
}