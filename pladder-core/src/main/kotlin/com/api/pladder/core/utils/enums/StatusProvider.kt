package com.api.pladder.core.utils.enums

interface StatusProvider {
    val stringStatus: String
    companion object {
        inline fun <reified T> fromStringStatus(stringStatus: String): T where T : Enum<T>, T : StatusProvider {
            return EnumUtils.fromStringStatus(T::class.java, stringStatus)
        }
    }
}