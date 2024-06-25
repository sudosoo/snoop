package com.api.pladder.application.dto.user.common.request

import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterUserReq (
    @Schema(description="가입 ID" , example = "탐정 ID : 이메일 , 회원 ID : 닉네임")
    var id:String? = null,
    @Schema(description="비밀번호" , example = "1q2w3e4r")
    var passwd:String? = null,
    @Schema(description="핸드폰번호", example = "010-0000-0000")
    var phoneNumber: String? = null,
    @Schema(description = "유저 권한" , example = "User type DETECTIVE, CUSTOMER")
    val userType: UserType

) {
    fun updateConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }
}



