package com.api.pladder.presentation.controller.user

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.request.WithdrawnUserReq
import com.api.pladder.application.service.auth.AuthService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.anotation.user.ExplainRegisterUser
import com.api.pladder.presentation.anotation.user.ExplainUpdatePasswdUser
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "회원", description = "회원 관련 API")
@RequestMapping("/api")
class UserController (
    val service : AuthService
) : AuthDataProvider, ResponseEntityCreation {
    @ExplainRegisterUser
    @PostMapping(value = ["/detective/register","/customer/register"])
    fun register(request : RegisterUserReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.signUp(request, getAuthReq()))
    }

    @ExplainUpdatePasswdUser
    @PutMapping(value = ["/detective/register","/customer/register"])
    fun updatePasswd(request : UpdatePasswdUserReq):ResponseEntity<BaseResp>{
        return getRespEntity(service.updatePasswd(request,getAuthReq()))
    }

    @ExplainUpdatePasswdUser
    @DeleteMapping(value = ["/detective/register","/customer/register"])
    fun withdrawn(request : WithdrawnUserReq){
        service.withdrawn(request,getAuthReq())
    }

    //TODO
    /*@ExplainSaveProfile
    @PostMapping(value = [ "/detective/image"], consumes = ["multipart/form-data"])
    fun updateProfile(request : UpdatePasswdUserReq){
        service.updateProfile(request,getAuthReq())
    }*/
}