package com.api.pladder.application.service.user.boss

import com.api.pladder.application.dto.auth.request.SignInReq
import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.service.user.UserService
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.manager.CustomerReader
import org.springframework.stereotype.Service

@Service
class BossService : UserService {

    private lateinit var customerManager: CustomerManager
    private lateinit var customerReader: CustomerReader

    override fun findByEmail(email: String): UserResp {
        return UserResp(customerReader.findByEmail(email))
    }

    override fun validate(req: SignInReq): Boolean {
        TODO("Not yet implemented")
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }


}