package com.api.pladder.application.service.user

import com.api.pladder.application.dto.auth.request.SignInReq
import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.domain.entity.user.User

interface UserService{
    fun findByEmail(email: String): User
    fun withdraw(userId: String): WithdrawResp
    fun validate(req: SignInReq): Boolean

}