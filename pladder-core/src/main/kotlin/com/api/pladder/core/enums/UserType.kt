package com.api.pladder.core.enums

enum class UserType(
    val authorization: String,
    val requestMapper: String) {

    ADMIN("admin", "/api/admin"),
    DETECTIVE("detective", "/api/detective"),
    CUSTOMER("customer", "/api/customer"),
    UNKNOWN("open", "/api/open");

    companion object {
        fun fromString(value: String): UserType {
            return values().find { it.name.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("존재하지 않는 유저타입 입니다: $value")
        }
    }
}
