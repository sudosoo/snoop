package com.api.pladder.application.dto.auth.request

import com.api.pladder.application.auth.enums.UserType
import com.api.pladder.application.dto.user.UserResp

data class AuthReq(
    val userType: UserType = UserType.UNKNOWN,

    val userId: String? = null,
) {
    constructor(response: UserResp, userType: UserType) : this(
        userId = response.id,
        userType = userType
    )
}