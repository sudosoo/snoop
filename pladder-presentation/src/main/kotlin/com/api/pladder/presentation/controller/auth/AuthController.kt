package com.api.pladder.presentation.controller.auth

import com.api.pladder.application.dto.auth.request.SignInUserReq
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.service.auth.AuthService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.auth.*
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "사용자", description = "사용자 관련 API")
@RequestMapping("/api")
class AuthController (
    val service : AuthService,
) : AuthDataProvider, ResponseEntityCreation {
    @ExplainRegisterUser
    @PostMapping(value = ["/detective/user","/customer/user"])
    fun register(request : RegisterUserReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.signUp(request))
    }

    @ExplainSignIn
    @GetMapping(value = ["/detective/signIn","/customer/signIn"])
    fun signIn(request : SignInUserReq, servletResp: HttpServletResponse) : ResponseEntity<BaseResp> {
        return getRespEntity(service.signIn(request, servletResp))
    }

    @ExplainUpdatePasswdUser
    @PutMapping(value = ["/detective/updatePasswd","/customer/updatePasswd"])
    fun updatePasswd(request : UpdatePasswdUserReq):ResponseEntity<BaseResp>{
        return getRespEntity(service.updatePasswd(request,getAuthReq()))
    }

    @ExplainWithdrawnUser
    @DeleteMapping(value = ["/detective/user","/customer/user"])
    fun withdrawn() : ResponseEntity<BaseResp>{
        return getRespEntity(service.withdrawn(getAuthReq()))
    }

    @ExplainValidUser
    @GetMapping(value = ["/detective/confirm","/customer/confirm"])
    fun confirmPasswd(@RequestParam passwd : String) :  ResponseEntity<BaseResp>{
        return getRespEntity(service.validUser(passwd, getAuthReq()))
    }


    //TODO
    /*@ExplainSaveProfile
    @PostMapping(value = [ "/detective/image"], consumes = ["multipart/form-data"])
    fun updateProfile(request : UpdatePasswdUserReq){
        service.updateProfile(request,getAuthReq())
    }*/
}