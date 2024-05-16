package com.api.pladder.application.service.auth.security

import com.api.pladder.application.auth.jwt.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityManager(
    val jwtUtil: JwtUtil
    val
) {
    fun setContextAuthentication(email: String) {
        val context = SecurityContextHolder.createEmptyContext()
        val authentication = createAuthentication(email)
        context.authentication = authentication
        SecurityContextHolder.setContext(context)
    }

    fun createAuthentication(email: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(email)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }


}