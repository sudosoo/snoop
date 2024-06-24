package com.api.pladder.application.service.user.common

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import java.util.*

interface UserService{
    fun register(request: RegisterUserReq): UserResp
    fun findByEmail(email: String): UserResp
    fun withdrawn(userId: UUID)
    fun updatePasswd(userId: UUID, request: UpdatePasswdUserReq): UserResp
    fun validUser(userId: UUID, passwd:String) :Boolean
}