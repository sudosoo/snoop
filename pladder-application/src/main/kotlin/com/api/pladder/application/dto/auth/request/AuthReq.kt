package com.api.pladder.application.dto.auth.request

import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.domain.entity.user.enums.UserType
import java.util.*

//@Schema(name = "Auth-Request")
data class AuthReq(
    //@Schema(description = "User ID")
    val userId: UUID? = null,
    //@Schema(description = "User type (DETECTIVE, CUSTOMER, ADMIN, UNKNOWN)")
    val userType: UserType = UserType.UNKNOWN,
    ) {
    constructor(response: UserResp, userType: UserType) : this(
        userId = response.userId,
        userType = userType
    )
}
