package com.api.pladder.application.auth.filters

import jakarta.servlet.http.HttpServletRequest
import com.api.pladder.application.auth.jwt.JwtUtil
import com.api.pladder.application.core.enums.HeaderPrefix.AUTHORIZATION
import com.api.pladder.application.core.enums.HeaderPrefix.REFRESHTOKEN
import com.api.pladder.application.core.enums.HeaderPrefix.REFRESHToken
import com.api.pladder.application.dto.auth.request.AuthReq
import com.api.pladder.application.service.http.HttpResolver
import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val resolver : HttpResolver
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var refreshToken: String? = null;
        val token = resolver.resolveToken(request)
        if (token != null) {
            if (!jwtUtil.validate(token)) {
               //TODO refresh token
                 val cookies = request.cookies
                for (cookie in cookies) {
                    if (cookie.name == REFRESHTOKEN) {
                        refreshToken = cookie.value
                        break
                    } else {
                        return
                    }
                }
                val atInfo = authenticatedUserByToken(token)
                if (jwtUtil.getRefreshTokenIsTrue(atInfo.subject, refreshToken)) {
                    val member = jwtUtil.authenticatedUser(atInfo.subject)
                    response.addHeader(
                        AUTHORIZATION,
                        jwtUtil.createAccessToken(user.email,)
                    )
                }
            }
            authenticatedUserByToken(token)
        }
        filterChain.doFilter(request, response)
    }

    fun setSecurityContext(authReq: AuthReq) {
        val authentication = UsernamePasswordAuthenticationToken(
            authReq,
            null,
            getAuthorities(authReq)
        )
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun extractAuthRequest(request: HttpServletRequest) : AuthReq {
        val token = request.getHeader("authorization")
            ?: throw IllegalArgumentException("Token is required")
        return jwtUtil.convertToRequest(token)
    }

    fun getAuthorities(authReq : AuthReq): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(authReq.userType.authorization))
    }





}