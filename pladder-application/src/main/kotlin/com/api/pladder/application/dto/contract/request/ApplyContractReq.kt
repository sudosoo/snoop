package com.api.pladder.application.dto.contract.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class ApplyContractReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="선금", example = "500000")
    var advanceDeposit : Int = 0,
    @Schema(description="수임료", example = "5000000")
    var pee : Int = 0,
    @Schema(description="조사 시작일", example = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var startPeriod: LocalDate? = null,
    @Schema(description="조사 종료일", example = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var endPeriod : LocalDate ?= null,
)