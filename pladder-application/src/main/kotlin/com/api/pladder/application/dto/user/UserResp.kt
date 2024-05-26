package com.api.pladder.application.dto.user

import com.api.pladder.domain.entity.user.Admin
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective
import java.util.*

class UserResp(
    val userId: UUID,
    val status : String
) {
    constructor(model: Customer) : this(
        userId = model.id,
        status = "customer"
    )
    constructor(model: Detective) : this(
        userId = model.id,
        status = "detective"

    )

    constructor(model: Admin) : this(
        userId = model.id,
        status = "admin"
    )

}