package com.api.pladder.application.service.user.common

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp

interface UserService{
    fun register(req: RegisterUserReq): UserResp
    fun findByEmail(email: String): UserResp
    fun withdrawn(userId: String)
    fun updatePasswd(req: UpdatePasswdUserReq): UserResp
    fun isUser(email :String,passwd :String) : Boolean
}