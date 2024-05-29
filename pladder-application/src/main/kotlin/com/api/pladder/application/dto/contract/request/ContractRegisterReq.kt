package com.api.pladder.application.dto.contract.request


class ContractRegisterReq (
    val clientId :String,
    val companyId :String,
    var advance : String? = null,
    //용도(고소 등)
    val purpose : String,
    //
    val solution : String,
    val descriptor: String,
    val pee : String,

){
}

