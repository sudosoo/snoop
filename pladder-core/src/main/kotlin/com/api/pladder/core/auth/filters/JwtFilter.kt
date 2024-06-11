package com.api.pladder.core.auth.filters

import com.api.pladder.core.auth.security.SecurityManager
import com.api.pladder.core.enums.ResourceType
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.http.HttpResolver
import com.api.pladder.core.utils.jwt.JwtUtil
import io.jsonwebtoken.MalformedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.security.SignatureException
import kotlin.io.AccessDeniedException as AccessDeniedException1

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
        try {
            val resourceType = ResourceType.getResourceType(request.requestURI)
            if (resourceType.needToken){
                /**
                 * check token and set security-context
                 */
                val authReq = httpResolver.getUserInfoByToken(request)
                securityManager.setContextAuthentication(authReq!!)

                /**
                 * check diff token with url
                 * 클라이언트가 알맞은 엔드포인트를 요청했는지 확인해야 함.
                 * 예를 들어서, detective 타입의 사용자는 /api/detective 로 시작하는 엔드포인트로만 접근할 수 있음.
                 */
                if (!request.requestURI.startsWith(authReq.userType.requestMapper))
                    throw AccessDeniedException(
                        """
                        ${authReq.userType}타입의 사용자에게 허용되지 않은 리소스입니다. 
                    """.trimIndent()
                    )
            } else if (resourceType.needAuthReq) {
                securityManager.setContextAuthentication(AuthUserObject(userType = UserType.UNKNOWN))
            }
        } catch (e: IllegalArgumentException) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: Token is required")
            return
        } catch (e : MalformedJwtException){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: Invalid token")
            return
        } catch (e : SignatureException){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: ${e.message}")
            return
        } catch (e : AccessDeniedException1){
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: ${e.message}")
            return
        } catch (e: Exception) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error")
            return
        }
        filterChain.doFilter(request, response)
    }



}