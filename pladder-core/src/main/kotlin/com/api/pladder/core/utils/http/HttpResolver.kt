package com.api.pladder.core.utils.http

import com.api.pladder.core.enums.HeaderPrefix
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.jwt.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class HttpResolver (
    val jwtUtil: JwtUtil
){
    fun getUserInfoByToken(request: HttpServletRequest): AuthUserObject? {
        val token = request.getHeader(HeaderPrefix.AUTHORIZATION)
            ?: throw IllegalArgumentException("Token is required")
        return jwtUtil.convertToRequest(token)
    }

}