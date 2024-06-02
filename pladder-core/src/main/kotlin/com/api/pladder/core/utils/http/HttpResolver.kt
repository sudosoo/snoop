package com.api.pladder.core.utils.http

import com.api.pladder.core.enums.HeaderPrefix
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class HttpResolver {
    fun tokenResolve(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HeaderPrefix.AUTHORIZATION)
        return if (!bearerToken.isNullOrBlank() && bearerToken.startsWith(HeaderPrefix.BEARER)) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}