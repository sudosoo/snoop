package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.SuggestContractReq
import com.api.pladder.application.dto.contract.request.UpdateContractContentReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.user.Customer

object ContractDtoMapper {
    fun toEntity(company: Company, customer: Customer, request: RegisterContractReq): Contract {
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

    fun updateSuggestContent(contract: Contract, request: SuggestContractReq): Contract {
        if (request.advanceDeposit != 0) contract.advanceDeposit = request.advanceDeposit
        if (request.pee != 0) contract.pee = request.pee
        if (request.description != null) contract.description = request.description
        return contract
    }

    fun updateContent(contract: Contract, request: UpdateContractContentReq): Contract {
        if (request.incidentLocation != null) contract.contractContent.incidentLocation = request.incidentLocation
        if (request.incidentTime != null) contract.contractContent.incidentTime = request.incidentTime
        return contract
    }


}