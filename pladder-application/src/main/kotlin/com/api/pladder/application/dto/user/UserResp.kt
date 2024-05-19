package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.Admin
import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.enums.UserStatus

class UserResp(
    val userId: String,
    val status: UserStatus?,
) {
    constructor(model: Customer) : this(
        userId = model.id,
        status = model.status,
    )
    constructor(model: Boss) : this(
        userId = model.id,
        status = model.status,
    )

    constructor(model: Admin) : this(
        userId = model.id,
        status = null,
    )

}