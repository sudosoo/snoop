package com.api.pladder.application.dto.contractContent.request

import com.api.pladder.domain.entity.user.enums.Filed

class RegisterContractContentReq (
    val contractId :String,
    val content : String,
    val contractField : Filed,
    var incidentLocation: String? = null,
    var incidentTime : String
){
}