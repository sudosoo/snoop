package com.api.pladder.presentation.controller.auth

import com.api.pladder.application.dto.auth.request.SignInUserReq
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.service.auth.AuthService
import com.api.pladder.presentation.common.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/login")
class AuthController(
    private val service: AuthService

): AuthDataProvider, ResponseEntityCreation {
    @PostMapping(value = ["/customer/signup, /detective/signup"])
    fun signUp(req : RegisterUserReq, request: HttpServletRequest) {
        val encoder = BCryptPasswordEncoder()
        val url = request.requestURI
        val convertPasswd = encoder.encode(req.passwd)
        req.updateConvertPasswd(convertPasswd)

        when {
            url.endsWith("/customer/signup") -> req.setTypeGeneratedCustomer()
            url.endsWith("/detective/signup") -> req.setTypeGeneratedDetective()
            else -> throw IllegalArgumentException("Invalid signup URL")
        }
        service.signUp(req)
    }


    @GetMapping(value = ["/open/signIn"])
    fun signIn(req: SignInUserReq,
               request: HttpServletRequest,
               servletResp: HttpServletResponse)
    : ResponseEntity<BaseResp> {

        val encoder = BCryptPasswordEncoder()
        val convertPasswd = encoder.encode(req.passwd)
        req.updateConvertPasswd(convertPasswd)
        val url = request.requestURI

        when {
            url.endsWith("/customer/signup") -> req.setTypeGeneratedCustomer()
            url.endsWith("/detective/signup") -> req.setTypeGeneratedDetective()
            else -> throw IllegalArgumentException("Invalid signup URL")
        }

        return getRespEntity(service.signIn(req,getAuthReq(),servletResp))
    }


    @GetMapping("/signOut")
    fun signOut() {
    }

}