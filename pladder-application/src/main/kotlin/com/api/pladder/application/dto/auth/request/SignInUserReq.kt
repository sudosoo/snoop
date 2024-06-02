package com.api.pladder.application.dto.auth.request

import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema

data class SignInUserReq (
    @Schema(description = "로그인 이메일", example = "abc@gamil.com")
    val email:String? = null,
    @Schema(description = "로그인 패스워드",example = "abc1q2w3e")
    var passwd:String? = null
) {
    lateinit var userType: UserType
    fun updateConvertPasswd(convertPass: String) {
        this.passwd = convertPass
    }

}
