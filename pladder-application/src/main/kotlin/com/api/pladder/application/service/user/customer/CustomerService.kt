package com.api.pladder.application.service.user.customer

import com.api.pladder.application.dto.user.common.response.WithdrawResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdatePasswdCustomerReq
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.manager.CustomerReader
import org.springframework.stereotype.Service

@Service
class CustomerService : UserService {
    private lateinit var manager: CustomerManager
    private lateinit var reader: CustomerReader

    override fun findByEmail(email: String): UserResp {
        return UserResp(reader.findByEmail(email))
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

    override fun register(req: RegisterUserReq) : UserResp{
        return UserResp(manager.register(req))
    }

    fun update(req: UpdateInfoCustomerReq): UserResp {
        return UserResp(manager.update(req))
    }

    fun updatePasswd(req: UpdatePasswdCustomerReq): UserResp {
        return UserResp(manager.updatePasswd(req))
    }


}