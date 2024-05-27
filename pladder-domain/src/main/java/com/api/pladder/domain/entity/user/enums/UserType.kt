package com.api.pladder.domain.entity.user.enums

enum class UserType(
    val authorization : String,
    val requestMapper : String,
) {
    ADMIN(authorization = "admin", requestMapper = "/api/admin"),
    DETECTIVE(authorization = "detective", requestMapper = "/api/detective"),
    CUSTOMER(authorization = "customer", requestMapper = "/api/customer"),
    UNKNOWN(authorization = "open", requestMapper = "/api/open");
    companion object {
        fun fromString(value: String): UserType {
            return requireNotNull(entries.find { it.name.equals(value, ignoreCase = true) }) {
                "존재하지 않는 유저타입 입니다: $value"
            }
        }
    }
}