package com.api.pladder.application.dto.user.customer.request

data class UpdateInfoCustomerReq (
    val userId: String,
    val email:String,
    val phoneNumber: String,
    val nickName: String
)



