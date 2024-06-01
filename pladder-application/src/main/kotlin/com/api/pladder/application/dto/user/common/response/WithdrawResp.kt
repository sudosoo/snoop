package com.api.pladder.application.dto.user.common.response

import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective

data class WithdrawResp(
    val email : String?
) {
    constructor(model: Detective) : this(
        email = model.email
    )

    constructor(model: Customer) : this(
        email = model.email
    )
}
