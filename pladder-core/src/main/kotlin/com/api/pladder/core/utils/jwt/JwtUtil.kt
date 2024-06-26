package com.api.pladder.core.utils.jwt

import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.TokenException
import com.api.pladder.core.obj.AuthUserObject
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*


@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    private val secret: String
){
    private val secretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    private val expiration = 864_000_000 // 10 days

    fun generate(authUserObject: AuthUserObject): String {
        return Jwts.builder()
            .claim("userId", authUserObject.userId)
            .claim("userType", authUserObject.userType)
            .signWith(secretKey)
            .compact()
    }


    fun validate(token : String) : Boolean {
        try {
            extractClaims(token)
            // TODO: Validate token based on service requirements
            return true
        } catch (e : MalformedJwtException){
            throw TokenException("토큰이 유효하지 않습니다.")
        } catch (e: SignatureException){
            throw TokenException("토큰이 유효하지 않습니다")
        } catch (e : Exception){
            throw e
        }
    }

    fun extractClaims(token: String) : Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }


    fun equalsTokenRefreshAndAccess(accessToken: String, refreshToken: String): Boolean {
        val accessClaims = extractClaims(accessToken)
        val refreshClaims = extractClaims(refreshToken)
        return accessClaims.get("userId", String::class.java) == refreshClaims.get("userId", String::class.java)
    }

    fun convertToRequest(token: String) : AuthUserObject {
        val claims = extractClaims(token)
        return AuthUserObject(
            userType = UserType.valueOf(
                claims.get("userType", String::class.java)
            ),
            userId = UUID.fromString(claims.get("userId", String::class.java))
        )
    }


}