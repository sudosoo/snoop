package com.api.pladder.application.dto.auth.request

import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema

data class SignInUserReq (
    @Schema(description = "로그인 Id", example = "탐정 : 이메일 , 회원 : 닉네임")
    val id:String,

    @Schema(description = "로그인 패스워드",example = "abc1q2w3e")
    var passwd:String,

    @Schema(description = "로그인 유저 타입",example = "User type DETECTIVE, CUSTOMER")
    val userType: UserType
) {
    fun updateConvertPasswd(convertPass: String) {
        this.passwd = convertPass
    }

}
