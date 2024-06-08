package com.api.pladder.core.obj

import com.api.pladder.core.enums.UserType
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
@Schema(name = "Auth-Object")
data class AuthUserObject(
    @Schema(description = "User ID")
    var userId: UUID? = null,
    @Schema(description = "User type (DETECTIVE, CUSTOMER, ADMIN, UNKNOWN)")
    var userType: UserType = UserType.UNKNOWN
)
