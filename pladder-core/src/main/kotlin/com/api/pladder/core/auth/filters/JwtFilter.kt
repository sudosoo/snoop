package com.api.pladder.core.auth.filters

import com.api.pladder.core.auth.http.HttpResolver
import com.api.pladder.core.auth.obj.AuthUserObject
import com.api.pladder.core.jwt.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val httpResolver : HttpResolver
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        var refreshToken: String? = null;
        val accessToken = httpResolver.tokenResolve(request)
        if (accessToken != null) {
            val authReq = jwtUtil.convertToRequest(accessToken)
            //TODO refresh token
            /*if (!jwtUtil.validate(accessToken)) {
                val cookies = request.cookies
                for (cookie in cookies) {
                    if (cookie.name == REFRESHTOKEN) {
                        refreshToken = cookie.value
                        break
                    } else {
                        return
                    }
                }
                requireNotNull(refreshToken, { "Refresh token is required" })
                if (jwtUtil.equalsTokenRefreshAndAccess(accessToken, refreshToken)) {
                    val reIssuance = jwtUtil.generate(authReq.userId!!, authReq.userType)
                    response.addHeader(AUTHORIZATION,reIssuance)
                }
            }*/
            setSecurityContext(authReq)
        }

        filterChain.doFilter(request, response)
    }

    fun setSecurityContext(authUserObject: AuthUserObject) {
        val authentication = UsernamePasswordAuthenticationToken(
            authUserObject,
            null,
            getAuthorities(authUserObject)
        )
        SecurityContextHolder.getContext().authentication = authentication
    }

    fun getAuthorities(authUserObject : AuthUserObject): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(authUserObject.userType.stringStatus))
    }





}