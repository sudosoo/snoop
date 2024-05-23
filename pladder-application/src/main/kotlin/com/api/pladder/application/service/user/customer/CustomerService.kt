package com.api.pladder.application.service.user.customer

import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.dto.user.customer.request.RegisterCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdatePasswdCustomerReq
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.manager.CustomerReader
import com.api.pladder.domain.entity.user.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Service
class CustomerService : UserService {
    private lateinit var customerManager: CustomerManager
    private lateinit var customerReader: CustomerReader

    override fun findByEmail(email: String): User {
        return customerReader.findByEmail(email)
    }

    override fun withdraw(userId: String): WithdrawResp {
        TODO("Not yet implemented")
    }

    fun register(req: RegisterCustomerReq) {
        customerManager.register(req)
    }

    fun update(req: UpdateInfoCustomerReq): UserResp {
        return customerManager.update(req)
    }

    fun updatePasswd(req: UpdatePasswdCustomerReq): UserResp {
        return customerManager.updatePasswd(req)
    }


}