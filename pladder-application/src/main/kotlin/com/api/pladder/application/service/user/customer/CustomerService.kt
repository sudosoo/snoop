package com.api.pladder.application.service.user.customer

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.reader.CustomerReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    val manager: CustomerManager,
    val reader: CustomerReader
) : UserService {
    override fun findByEmail(email: String): UserResp {
        return UserResp(reader.findByEmail(email))
    }

    override fun withdrawn(userId: UUID) {
        manager.deleteById(userId)
    }

    override fun register(request: RegisterUserReq) : UserResp{
        return UserResp(manager.register(request))
    }

    fun updateInfo(requestUserId: UUID , request: UpdateInfoUserReq): UserResp {
        return UserResp(manager.updateInfo(requestUserId, request))
    }

    override fun updatePasswd(userId: UUID, request: UpdatePasswdUserReq): UserResp {
        return UserResp(manager.updatePasswd(userId,request))
    }

    override fun validUser(userId: UUID, passwd: String): Boolean {
        return reader.findById(userId).passwd == passwd
    }


}