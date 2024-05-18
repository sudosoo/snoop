package com.api.pladder.application.service.user.boss

import com.api.pladder.application.dto.auth.request.SignInReq
import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.dto.user.boss.request.RegisterBossReq
import com.api.pladder.application.dto.user.customer.request.UpdateInfoBossReq
import com.api.pladder.application.service.user.UserService
import com.api.pladder.application.service.user.boss.manager.BossManager
import com.api.pladder.application.service.user.boss.manager.BossReader
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.manager.CustomerReader
import com.api.pladder.domain.entity.user.User
import org.springframework.stereotype.Service

@Service
class BossService : UserService {

    private lateinit var bossManager: BossManager
    private lateinit var bossReader: BossReader

    override fun findByEmail(email: String): User {
        return bossManager.findById(email)
    }

    override fun validate(req: SignInReq): Boolean {
        TODO("Not yet implemented")
    }

    fun register(req: RegisterBossReq) {
        bossManager.register(req)
    }

    fun update(req: UpdateInfoBossReq) {
        bossManager.update(req)
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }


}