package com.api.pladder.application.service.user.common

import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.common.response.WithdrawResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq

interface UserService{
    fun register(req: RegisterUserReq): UserResp
    fun findByEmail(email: String): UserResp
    fun withdraw(userId: String): WithdrawResp

}