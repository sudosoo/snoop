package com.api.pladder.application.dto.auth.request

import com.api.pladder.application.auth.enums.UserType
import com.api.pladder.domain.entity.user.enums.AuthChannel

data class SignInReq(
    val userType: UserType,
    var email: String,
    val passwd: String,
    val channel: AuthChannel,
)