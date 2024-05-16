package com.api.pladder.application.service.user.admin

import com.api.pladder.application.dto.auth.request.SignInReq
import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.service.user.UserService
import com.api.pladder.application.service.user.admin.manager.AdminReader
import com.api.pladder.domain.entity.user.Admin
import com.api.pladder.domain.entity.user.User
import org.springframework.stereotype.Service

@Service
class AdminService : UserService{
    private lateinit var adminReader: AdminReader

    override fun findByEmail(email: String): User {
        return adminReader.findByEmail(email)
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

    override fun validate(req: SignInReq): Boolean {
        TODO("Not yet implemented")
    }
}