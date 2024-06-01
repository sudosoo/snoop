package com.api.pladder.core.auth.obj

import com.api.pladder.core.enums.UserType
import java.util.*

data class AuthObject(
    val userId: UUID,
    val userType: UserType = UserType.UNKNOWN
    ) {
    /*constructor(response: UserResp, userType: UserType) : this(
        userId = response.userId,
        userType = userType
    )*/
}
