package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.UserStatus

class UserResp(
    val email: String?,
    val status: UserStatus,
) {
    constructor(model: Customer) : this(
        email = model.email,
        status = model.status,
    )

    constructor(model: Boss) : this(
        email = model.email,
        status = model.status,
    )
}