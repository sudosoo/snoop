package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.UserStatus

class UserResp(
    private val userId: String?,
    private val status: UserStatus,
) {
    constructor(model: Customer) : this(
        userId = model.id,
        status = model.status,
    )

    constructor(model: Boss) : this(
        userId = model.id,
        status = model.status,
    )
}