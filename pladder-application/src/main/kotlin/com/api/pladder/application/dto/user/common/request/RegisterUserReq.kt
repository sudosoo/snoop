package com.api.pladder.application.dto.user.common.request

import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema

data class RegisterUserReq (
    @Schema(description="가입 이메일" , example = "aaa@naver.com")
    val email:String? = null,
    @Schema(description="가입 패스워드" , example = "password")
    var passwd:String? = null,
    @Schema(description="핸드폰번호", example = "010-0000-0000")
    var phoneNumber: String? = null,
    @Schema(description="닉네임" , example = "뽀삐")
    val nickname: String? = null,
    @Schema(description = "유저 권한" , example = "User type DETECTIVE, CUSTOMER, ADMIN")
    val userType: UserType

) {
    fun updateConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }
}



