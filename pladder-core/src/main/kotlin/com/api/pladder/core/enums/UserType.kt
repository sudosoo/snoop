package com.api.pladder.core.enums

enum class UserType (
    val stringStatus: String,
    val requestMapper: String
){
    ADMIN("admin", "/api/admin"),
    DETECTIVE("detective", "/api/detective"),
    CUSTOMER("customer", "/api/customer"),
    UNKNOWN("open", "/api/open");

}
