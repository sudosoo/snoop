package com.api.pladder.application.service.auth.security

import com.api.pladder.application.dto.auth.request.AuthReq
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityManager {
    fun setContextAuthentication(authReq: AuthReq) {
        val authentication = UsernamePasswordAuthenticationToken(
            authReq.userId,
            null,
            getAuthorities(authReq)
        )
        SecurityContextHolder.getContext().authentication = authentication
    }
    fun getAuthorities(authReq : AuthReq): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(authReq.userType.authorization))
    }



}