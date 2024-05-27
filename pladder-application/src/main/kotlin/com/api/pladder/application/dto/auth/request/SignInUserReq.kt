package com.api.pladder.application.dto.auth.request

import com.api.pladder.application.dto.auth.common.UserTypeAppender
import com.api.pladder.domain.entity.user.enums.UserType

data class SignInUserReq (
    val email:String? = null,
    var passwd:String? = null
): UserTypeAppender {
    lateinit var userType: UserType
    fun updateConvertPasswd(convertPass: String) {
        this.passwd = convertPass
    }

}
