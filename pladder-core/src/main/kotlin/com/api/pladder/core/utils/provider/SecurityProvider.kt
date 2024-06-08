package com.api.pladder.core.utils.provider

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityProvider {
    fun passwdBCryptConvert(rawPass: String): String {
        val encoder = BCryptPasswordEncoder()
        return encoder.encode(rawPass)
    }
}