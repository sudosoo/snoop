package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.UpdateContractContentReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.user.Customer

object ContractDtoMapper {
    fun toEntity(req : RegisterContractReq, company: Company ,customer: Customer) : Contract {
        return Contract(
            customer.customerId,
            company,
            req.advanceDeposit,
            req.pee,
            req.purpose,
            req.requestSolution,
            customer.nickName,
            customer.phoneNumber
        )
    }

    fun updateContent(contract: Contract , request : UpdateContractContentReq){
        contract.contentUpdate(request.contractField,request.incidentLocation,request.incidentTime)
    }

}