package com.api.pladder.application.service.auth

import com.api.pladder.TestData.authReqAdmin
import com.api.pladder.core.utils.jwt.JwtUtil
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
@DisplayName("사용자의 tokenService 생성 검증")
class JwtTokenService : JwtTokenServiceTestForUser{

    @Autowired
    private lateinit var service: JwtUtil
    override fun getTokenService() = service

    /**
     * create Token
     */
    @Test
    @DisplayName("토큰을 생성한다.")
    fun generateTokenForAdmin() {
        generateToken(authReqAdmin)
    }


    /**
     * validate Token
     */

    @Test
    @DisplayName("유효한 토큰을 검증한다.")
    fun validateTokenForAdmin() {
        validateToken(authReqAdmin)
    }

    @Test
    @DisplayName("조작된 토큰을 검증한다.")
    fun invalidateTokenForAdmin() {
        invalidateToken(authReqAdmin)
    }

    /**
     * convertToRequest
     */

    @Test
    @DisplayName("유효한 토큰에서 사용자 정보를 추출한다.")
    fun convertValidateTokenToRequestForAdmin() {
        convertValidateTokenToRequest(
            authReqForToken = authReqAdmin,
            userId = "admin"
        )
    }

    @Test
    @DisplayName("조작된 토큰에서 사용자 정보를 추출한다.")
    fun convertInvalidateTokenToRequestForAdmin() {
        convertInvalidateTokenToRequest(
            authReqForToken = authReqAdmin,
        )
    }
}