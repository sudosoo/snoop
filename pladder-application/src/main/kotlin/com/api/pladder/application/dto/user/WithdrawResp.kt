package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.enums.AuthChannel
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.entity.user.Customer

data class WithdrawResp(
    val email : String?,
    val channel: AuthChannel?,
) {
    constructor(model: Detective) : this(
        email = model.email,
        channel = model.authChannel,
    )

    constructor(model: Customer) : this(
        email = model.email,
        channel = model.authChannel,
    )
}
