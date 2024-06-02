package com.api.pladder.core.obj

import com.api.pladder.core.enums.UserType
import java.util.*

data class AuthUserObject(
    val userId: UUID,
    val userType: UserType = UserType.UNKNOWN
)
