package com.api.pladder.application.dto.user.boss.request

data class RegisterBossReq (
    val email:String,
    var passwd :String,
    val phoneNumber: String,
    val nickName: String
){
    fun setConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }
}



