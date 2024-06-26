package com.api.pladder.application.dto.user.customer.mapper

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.domain.entity.user.Customer

object CustomerDtoMapper {
    fun toEntity(request : RegisterUserReq) : Customer {
        return Customer(request.id,request.passwd)

    }

    fun updateInfo(customer: Customer, request: UpdateInfoUserReq) {
        return customer.updateInfo(request.phoneNumber)
    }

}