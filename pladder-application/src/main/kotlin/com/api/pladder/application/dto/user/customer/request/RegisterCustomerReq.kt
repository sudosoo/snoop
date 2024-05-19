package com.api.pladder.application.dto.user.customer.request

data class RegisterCustomerReq (
    val email:String,
    var passwd:String,
    val phoneNumber: String,
    val nickName: String
){
    fun setConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }

}



