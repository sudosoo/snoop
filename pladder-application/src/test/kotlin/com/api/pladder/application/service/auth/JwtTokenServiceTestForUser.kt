package com.api.pladder.application.service.auth

import com.api.pladder.core.exception.TokenException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.jwt.JwtUtil
import org.junit.jupiter.api.Assertions
import org.springframework.transaction.annotation.Transactional

interface JwtTokenServiceTestForUser{

    /**
     * etc
     */
    fun getTokenService(): JwtUtil

    /**
     * create Token
     */
    @Transactional
    fun generateToken(authReqForToken: AuthUserObject){
        // given
        val tokenService = getTokenService()

        // when
        val token = tokenService.generate(authReqForToken)
        print(token)
    }

    /**
     * validate Token
     */
    @Transactional
    fun validateToken(authReqForToken: AuthUserObject) {
        // given
        val tokenService = getTokenService()
        val token = tokenService.generate(authReqForToken)

        // when
        var result = false
        Assertions.assertDoesNotThrow {
            result = tokenService.validate(token)
        }

        // then
        Assertions.assertEquals(true, result)
    }

    @Transactional
    fun invalidateToken(authReqForToken: AuthUserObject) {
        // given
        val tokenService = getTokenService()
        val token = tokenService.generate(authReqForToken)
        val newToken = "${token}test"

        // when
        Assertions.assertThrows(TokenException::class.java) {
            val result = tokenService.validate(newToken)
        }
    }

    /**
     * convertToRequest
     */
    @Transactional
    fun convertValidateTokenToRequest(authReqForToken: AuthUserObject, userId: String){
        // given
        val tokenService = getTokenService()
        val token = tokenService.generate(authReqForToken)

        // when
        val result = tokenService.convertToRequest(token)

        // then
        Assertions.assertEquals(authReqForToken.userType, result.userType)
        Assertions.assertEquals(userId, result.userId)
    }

    @Transactional
    fun convertInvalidateTokenToRequest(authReqForToken: AuthUserObject) {
        // given
        val tokenService = getTokenService()
        val token = tokenService.generate(authReqForToken)
        val newToken = "${token}test"

        // when
        Assertions.assertThrows(TokenException::class.java) {
            val result = tokenService.convertToRequest(newToken)
        }
    }


}