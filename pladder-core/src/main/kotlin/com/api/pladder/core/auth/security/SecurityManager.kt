package com.api.pladder.core.auth.security

import com.api.pladder.core.auth.obj.AuthObject
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityManager {
    fun setContextAuthentication(authObject: AuthObject) {
        val authentication = UsernamePasswordAuthenticationToken(
            authObject.userId,
            null,
            getAuthorities(authObject)
        )
        SecurityContextHolder.getContext().authentication = authentication
    }
    fun getAuthorities(authObject : AuthObject): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(authObject.userType.authorization))
    }



}