package com.api.pladder.application.service.user.customer

import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.customer.request.RegisterCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdatePasswdCustomerReq
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.manager.CustomerReader
import org.springframework.stereotype.Service

@Service
class CustomerService{
    private lateinit var customerManager: CustomerManager
    private lateinit var customerReader: CustomerReader

    fun register(req: RegisterCustomerReq): UserResp {
        return customerManager.register(req)
    }

    fun update(req: UpdateInfoCustomerReq): UserResp {
        return customerManager.updateInfo(req)
    }

    fun updatePasswd(req: UpdatePasswdCustomerReq): UserResp {
        return customerManager.updatePasswd(req)
    }
}