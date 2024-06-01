package com.api.pladder.application.dto.auth.request

import com.api.pladder.application.dto.user.common.UserTypeAppender
import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema

data class SignInUserReq (
    @Schema(description = "User Email")
    val email:String? = null,
    @Schema(description = "User Passwd")
    var passwd:String? = null
): UserTypeAppender {
    lateinit var userType: UserType
    fun updateConvertPasswd(convertPass: String) {
        this.passwd = convertPass
    }

}
