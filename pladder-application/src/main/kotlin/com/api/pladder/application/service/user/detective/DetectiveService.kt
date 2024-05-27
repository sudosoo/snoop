package com.api.pladder.application.service.user.detective

import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.detective.request.UpdateInfoBossReq
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.detective.manager.DetectiveManager
import com.api.pladder.application.service.user.detective.manager.DetectiveReader
import com.api.pladder.domain.entity.user.User
import org.springframework.stereotype.Service

@Service
class DetectiveService : UserService {

    private lateinit var detectiveManager: DetectiveManager
    private lateinit var detectiveReader: DetectiveReader

    override fun findByEmail(email: String): User {
        return detectiveManager.findById(email)
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

    override fun register(req: RegisterUserReq): UserResp {
        return UserResp(detectiveManager.register(req))
    }

    fun update(req: UpdateInfoBossReq) {
        detectiveManager.update(req)
    }

}