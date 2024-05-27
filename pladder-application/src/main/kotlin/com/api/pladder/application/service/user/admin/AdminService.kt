package com.api.pladder.application.service.user.admin

import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.admin.manager.AdminReader
import com.api.pladder.domain.entity.user.User
import org.springframework.stereotype.Service

@Service
class AdminService :UserService {
    private lateinit var adminReader: AdminReader

    override fun findByEmail(email: String): User {
        return adminReader.findByEmail(email)
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

}