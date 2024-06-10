package com.api.pladder.application.dto.user.customer.mapper

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.domain.entity.user.Customer

object CustomerDtoMapper {
    fun toEntity(req : RegisterUserReq) : Customer {
        return Customer(req.nickName,req.passwd)
    }

    fun updateInfo(customer: Customer, req: UpdateInfoUserReq) {
        return customer.updateInfo(req.nickName)
    }

}