package com.api.pladder.core.utils.securityProvider

import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.exception.TokenException
import org.springframework.security.core.context.SecurityContextHolder

interface AuthDataProvider {
    fun getAuthReq() : AuthUserObject {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication?.principal is AuthUserObject) {
            authentication.principal as AuthUserObject
        } else {
            throw TokenException("권한 정보가 없습니다.")
        }
    }
}