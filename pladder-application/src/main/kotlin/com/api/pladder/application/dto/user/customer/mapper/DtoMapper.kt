package com.api.pladder.application.dto.user.customer.mapper

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.domain.entity.user.Customer

object DtoMapper {
    fun toEntity(req : RegisterUserReq) : Customer {
        return Customer(req.email, req.passwd , req.phoneNumber, req.nickName)
    }

    fun updateInfo(customer: Customer, req: UpdateInfoCustomerReq) {
        return customer.updateInfo(req.nickName,req.phoneNumber)
    }

}