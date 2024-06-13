package com.api.pladder.application.dto.user.common.response

import com.api.pladder.domain.entity.user.Admin
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

class UserResp(
    @Schema(description="유저 ID")
    val userId: UUID,
    @Schema(description="유저 권한")
    val status : String,
) {
    constructor(model: Customer) : this(
        userId = model.customerId,
        status = "customer"
    )
    constructor(model: Detective) : this(
        userId = model.detectiveId,
        status = "detective"
    )

    constructor(model: Admin) : this(
        userId = model.adminId,
        status = "admin"
    )

}