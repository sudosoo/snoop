package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.AuthChannel
import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.UserStatus


data class WithdrawResp(
    val email : String?,
    val channel: AuthChannel?,
) {
    constructor(model: Boss) : this(
        email = model.email,
        channel = model.authChannel,
    )

    constructor(model: Customer) : this(
        email = model.email,
        channel = model.authChannel,
    )
}
