package com.api.pladder.application.service.user.boss

import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.dto.user.detective.request.RegisterBossReq
import com.api.pladder.application.dto.user.detective.request.UpdateInfoBossReq
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.boss.manager.BossManager
import com.api.pladder.application.service.user.boss.manager.BossReader
import com.api.pladder.domain.entity.user.User
import org.springframework.stereotype.Service

@Service
class DetectiveService : UserService {

    private lateinit var bossManager: BossManager
    private lateinit var bossReader: BossReader

    override fun findByEmail(email: String): User {
        return bossManager.findById(email)
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

    fun register(req: RegisterBossReq) {
        bossManager.register(req)
    }

    fun update(req: UpdateInfoBossReq) {
        bossManager.update(req)
    }

}