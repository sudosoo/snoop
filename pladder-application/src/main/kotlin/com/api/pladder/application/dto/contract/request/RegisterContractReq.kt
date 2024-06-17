package com.api.pladder.application.dto.contract.request

import io.swagger.v3.oas.annotations.media.Schema


class RegisterContractReq (
    @Schema(description="회사 ID", example = "CP100001" )
    var companyId :String,
    @Schema(description="선금", example = "500,000")
    var advanceDeposit : Int = 0,
    @Schema(description="수임료", example = "5,000,000")
    var pee : Int = 0,
    @Schema(description="계약 이유", example = "고소")
    val purpose : String,
    @Schema(description="원하는 해결방안", example = "고소에 필요한 증거를 수집해 주세요.")
    val requestSolution : String,
    @Schema(description="설명" , example = "얼굴이 보이게 찍어주세요.")
    val description: String,
)

