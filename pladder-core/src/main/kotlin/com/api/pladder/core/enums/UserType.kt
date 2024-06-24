package com.api.pladder.core.enums

import com.api.pladder.core.utils.enums.EnumUtils.fromStringStatus
import com.api.pladder.core.utils.enums.StatusProvider

enum class UserType (
    override val stringStatus: String,
    val requestMapper: String
) : StatusProvider {

    ADMIN("admin", "/api/admin"),
    DETECTIVE("detective", "/api/detective"),
    CUSTOMER("customer", "/api/customer"),
    UNKNOWN("open", "/api/open");

    fun fromStringStatus(status: String): UserType {
        return fromStringStatus(UserType::class.java, status)
    }

}
