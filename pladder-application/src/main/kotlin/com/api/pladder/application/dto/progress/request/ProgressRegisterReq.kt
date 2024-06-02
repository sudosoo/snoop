package com.api.pladder.application.dto.progress.request

import io.swagger.v3.oas.annotations.media.Schema

class ProgressRegisterReq (
    @Schema(description="계약서 ID")
    val contractId :String,
    @Schema(description="날짜")
    val date : String,
    @Schema(description="내용")
    val content : String
){

}
