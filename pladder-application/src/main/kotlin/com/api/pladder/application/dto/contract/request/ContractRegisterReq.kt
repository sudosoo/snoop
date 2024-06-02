package com.api.pladder.application.dto.contract.request

import io.swagger.v3.oas.annotations.media.Schema


class ContractRegisterReq (
    @Schema(description="의뢰인 ID")
    val clientId :String,
    @Schema(description="회사 ID")
    val companyId :String,
    @Schema(description="선금")
    var advanceDeposit : String? = null,
    @Schema(description="수임료")
    val pee : String,
    @Schema(description="계약 이유")
    //용도(고소 등)
    val purpose : String,
    @Schema(description="원하는 해결방안")
    //
    val requestSolution : String,
    @Schema(description="설명")
    val description: String,
)

