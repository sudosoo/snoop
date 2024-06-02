package com.api.pladder.core.auth.filters

import com.api.pladder.core.auth.security.SecurityManager
import com.api.pladder.core.utils.jwt.JwtUtil
import com.api.pladder.core.utils.http.HttpResolver
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val httpResolver : HttpResolver,
    private val securityManager: SecurityManager
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
            securityManager.setContextAuthentication(authReq)
        }
        filterChain.doFilter(request, response)
    }

}