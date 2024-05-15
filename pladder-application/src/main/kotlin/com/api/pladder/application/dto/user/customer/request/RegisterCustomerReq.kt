package com.api.pladder.application.dto.user.customer.request

data class RegisterCustomerReq (
    val email:String,
    val passwd :String,
    val phoneNumber: String,
    val nickName: String
)



