package com.api.pladder.application.dto.user.common.request

import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterUserReq (
    @Schema(description="가입 이메일")
    val email:String? = null,
    @Schema(description="가입 패스워드")
    var passwd:String? = null,
    @Schema(description="핸드폰번호")
    val phoneNumber: String? = null,
    @Schema(description="닉네임")
    val nickName: String ? = null
) {
    lateinit var userType: UserType

    fun updateConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }


}



