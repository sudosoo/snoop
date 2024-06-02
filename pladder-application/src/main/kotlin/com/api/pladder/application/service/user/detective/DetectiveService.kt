package com.api.pladder.application.service.user.detective

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.common.response.WithdrawResp
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.detective.manager.DetectiveManager
import com.api.pladder.application.service.user.detective.reader.DetectiveReader
import org.springframework.stereotype.Service

@Service
class DetectiveService : UserService {

    private lateinit var manager: DetectiveManager
    private lateinit var reader: DetectiveReader

    override fun findByEmail(email: String): UserResp {
        return UserResp(reader.findByEmail(email))
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

    override fun register(req: RegisterUserReq): UserResp {
        return UserResp(manager.register(req))
    }

    override fun updatePasswd(req: UpdatePasswdUserReq): UserResp {
        return UserResp(manager.updatePasswd(req))
    }

    override fun isUser(email: String, passwd: String): Boolean {
        return reader.isUser(email, passwd)
    }

    fun updateInfo(requestUserId : String, req: UpdateInfoUserReq): UserResp {
        return UserResp(manager.updateInfo(requestUserId, req))
    }




}