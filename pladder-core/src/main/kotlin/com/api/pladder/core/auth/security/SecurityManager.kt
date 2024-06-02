package com.api.pladder.core.auth.security

import com.api.pladder.core.obj.AuthUserObject
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityManager {
    fun setContextAuthentication(authUserObject: AuthUserObject) {
        val authentication = UsernamePasswordAuthenticationToken(
            authUserObject.userId,
            null,
            getAuthorities(authUserObject)
        )
        SecurityContextHolder.getContext().authentication = authentication
    }
    fun getAuthorities(authUserObject : AuthUserObject): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(authUserObject.userType.stringStatus))
    }



}