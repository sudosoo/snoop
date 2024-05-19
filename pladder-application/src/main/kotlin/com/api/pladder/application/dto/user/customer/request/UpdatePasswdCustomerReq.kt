package com.api.pladder.application.dto.user.customer.request

data class UpdatePasswdCustomerReq (
    val email:String,
    val reqUpdatePasswd: String
)