package com.api.pladder.presentation.controller.auth

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.service.auth.AuthService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.auth.ExplainRegisterUser
import com.api.pladder.presentation.anotation.auth.ExplainUpdatePasswdUser
import com.api.pladder.presentation.anotation.auth.ExplainValidUser
import com.api.pladder.presentation.anotation.auth.ExplainWithdrawnUser
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "로그인", description = "로그인 관련 API")
@RequestMapping("/api")
class AuthController (
    val service : AuthService,
) : AuthDataProvider, ResponseEntityCreation {
    @ExplainRegisterUser
    @PostMapping(value = ["/detective/user","/customer/user"])
    fun register(request : RegisterUserReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.signUp(request))
    }

    @ExplainUpdatePasswdUser
    @PutMapping(value = ["/detective/user","/customer/user"])
    fun updatePasswd(request : UpdatePasswdUserReq):ResponseEntity<BaseResp>{
        return getRespEntity(service.updatePasswd(request,getAuthReq()))
    }

    @ExplainWithdrawnUser
    @DeleteMapping(value = ["/detective/user","/customer/user"])
    fun withdrawn(){
        service.withdrawn(getAuthReq())
    }

    @ExplainValidUser
    @GetMapping(value = ["/detective/confirm","/customer/confirm"])
    fun confirmPasswd(@RequestParam passwd : String){
        service.validUser(passwd, getAuthReq())
    }



    //TODO
    /*@ExplainSaveProfile
    @PostMapping(value = [ "/detective/image"], consumes = ["multipart/form-data"])
    fun updateProfile(request : UpdatePasswdUserReq){
        service.updateProfile(request,getAuthReq())
    }*/
}