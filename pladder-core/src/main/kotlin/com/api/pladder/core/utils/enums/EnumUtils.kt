package com.api.pladder.core.utils.enums

object EnumUtils {
    fun <T> fromStringStatus(enumClass: Class<T>, status: String): T where T : StatusProvider {
        for (enumConstant in enumClass.enumConstants) {
            if (enumConstant.stringStatus == status) {
                return enumConstant
            }
        }
        throw IllegalArgumentException("Unknown status: $status")
    }
}
