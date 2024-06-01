package com.api.pladder.core.auth.provider

import com.api.pladder.core.auth.obj.AuthObject
import com.api.pladder.core.exception.TokenException
import org.springframework.security.core.context.SecurityContextHolder

interface AuthDataProvider {
    fun getAuthReq() : AuthObject {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication?.principal is AuthObject) {
            authentication.principal as AuthObject
        } else {
            throw TokenException("권한 정보가 없습니다.")
        }
    }
}