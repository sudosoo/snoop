package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.SuggestContractReq
import com.api.pladder.application.dto.contract.request.UpdateContractContentReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.contract.ContractContent
import com.api.pladder.domain.entity.user.Customer

object ContractDtoMapper {
    fun toEntity(company: Company, customer: Customer, request : RegisterContractReq) : Contract {
        return Contract(
            company,
            customer.customerId,
            customer.nickname,
            customer.phoneNumber,
            request.specialty,
            request.purpose,
            request.requestSolution,
            request.description
        )
    }

    fun suggest(contract: Contract, request : SuggestContractReq) : Contract{
        if (request.advanceDeposit != 0) contract.advanceDeposit = request.advanceDeposit
        if (request.pee != 0) contract.pee = request.pee
        if (request.startPeriod != null) contract.startPeriod = request.startPeriod
        if (request.endPeriod != null) contract.endPeriod = request.endPeriod
        if (request.description != null) contract.description = request.description
        return contract
    }

    fun updateContent(contract: Contract, request : UpdateContractContentReq){
        contract.updateContent(
            ContractContent(
                request.incidentLocation,
                request.incidentTime)
        )
    }

}