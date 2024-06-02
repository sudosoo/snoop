package com.api.pladder.presentation.controller.user

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.service.auth.AuthService
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.provider.AuthDataProvider
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "회원", description = "회원 관련 API")
@RequestMapping("/api")
class UserController (
    val service : AuthService
) : AuthDataProvider {
    @ExplainRegisterUser
    @PostMapping(value = ["/detective/register","/customer/register"])
    fun register( request:RegisterUserReq){
        service.signUp(request, getAuthReq())
    }
    @ExplainUpdatePasswdUser
    @PutMapping(value = ["/detective/register","/customer/register"])
    fun updatePasswd( request:UpdatePasswdUserReq){
        service.updatePasswd(request,getAuthReq())
    }



}