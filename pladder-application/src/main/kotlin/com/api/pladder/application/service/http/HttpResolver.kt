package com.api.pladder.application.service.http

import com.api.pladder.application.core.enums.HeaderPrefix
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class HttpResolver {
    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HeaderPrefix.AUTHORIZATION)
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith(HeaderPrefix.BEARER)) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}