package com.api.pladder.application.dto.contract.request

import io.swagger.v3.oas.annotations.media.Schema

class UpdateContractContentReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="선금", example = "500,000")
    var advanceDeposit : Int = 0,
    @Schema(description="수임료", example = "5,000,000")
    var pee : Int = 0,
    @Schema(description="사건 장소")
    var incidentLocation: String? = null,
    @Schema(description="사건 시간 yyyy-MM-dd HH:mm")
    var incidentTime : String ?= null
){
}