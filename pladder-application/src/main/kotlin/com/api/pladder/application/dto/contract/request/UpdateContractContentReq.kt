package com.api.pladder.application.dto.contract.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

class UpdateContractContentReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="사건 장소")
    var incidentLocation: String? = null,
    @Schema(description="사건 시간", example = "2021-08-01 12:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    var incidentTime : LocalDateTime ?= null,
)