package com.api.pladder.application.dto.user.common.response

import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective
import io.swagger.v3.oas.annotations.media.Schema

data class WithdrawResp(
    @Schema(name="탈퇴할 이메일")
    val email : String?
) {
    constructor(model: Detective) : this(
        email = model.email
    )

    constructor(model: Customer) : this(
        email = model.email
    )
}
