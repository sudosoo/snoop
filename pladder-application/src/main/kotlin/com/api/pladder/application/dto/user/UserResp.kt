package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.Admin
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.enums.UserActive
import java.util.*

class UserResp(
    val userId: UUID,
    val status: UserActive?,
) {
    constructor(model: Customer) : this(
        userId = model.id,
        status = model.isActive,
    )
    constructor(model: Detective) : this(
        userId = model.id,
        status = model.isActive,
    )

    constructor(model: Admin) : this(
        userId = model.id,
        status = null,
    )

}