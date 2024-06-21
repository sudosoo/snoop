package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.ApplyContractContentReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.user.Customer
import java.time.LocalDate

object ContractDtoMapper {
    fun toEntity(company: Company, customer: Customer, req : RegisterContractReq) : Contract {
        return Contract(
            company,
            customer.customerId,
            customer.nickName,
            customer.phoneNumber,
            req.specialty,
            req.purpose,
            req.requestSolution,
            req.description
        )
    }

    fun updateContent(contract: Contract, request : ApplyContractContentReq){
        contract.apply(
            request.advanceDeposit,
            request.pee,
            LocalDate.parse(request.startPeriod),
            LocalDate.parse(request.endPeriod))

    }

}