package com.api.pladder.application.auth

import com.api.pladder.application.core.exception.TokenException
import com.api.pladder.application.dto.auth.request.AuthReq
import org.springframework.security.core.context.SecurityContextHolder

interface AuthDataProvider {
    fun getAuthReq() : AuthReq {
        val authentication = SecurityContextHolder.getContext().authentication
        return if (authentication?.principal is AuthReq) {
            authentication.principal as AuthReq
        } else {
            throw TokenException("권한 정보가 없습니다.")
        }
    }
}