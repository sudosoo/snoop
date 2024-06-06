package com.api.pladder.application.dto.progressHistory.request

import io.swagger.v3.oas.annotations.media.Schema

class ProgressHistoryUpdateReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="내용")
    val content : String
)
