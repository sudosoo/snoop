package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective

object ContractDtoMapper {

    fun ContractToEntity(req : RegisterContractReq, detective: Detective, ) : Contract {
        return Contract();
    }

    fun ContractUpdateInfo(customer: Customer, req: UpdateInfoContractReq) {
        return customer.updateInfo(req.nickName,req.phoneNumber)
    }
}