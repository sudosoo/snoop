package com.api.pladder.application.dto.user.customer.mapper

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.domain.entity.user.Customer

object CustomerDtoMapper {
    fun customerToEntity(req : RegisterUserReq) : Customer {
        return Customer(req.email, req.passwd , req.phoneNumber, req.nickName)
    }

    fun customerUpdateInfo(customer: Customer, req: UpdateInfoUserReq) {
        return customer.updateInfo(req.nickName,req.phoneNumber)
    }

}