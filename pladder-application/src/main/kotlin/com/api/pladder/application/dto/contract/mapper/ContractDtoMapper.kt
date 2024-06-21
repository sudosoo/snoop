package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.ApplyContractReq
import com.api.pladder.application.dto.contract.request.RegisterContractContentReq
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.contract.ContractContent
import com.api.pladder.domain.entity.user.Customer

object ContractDtoMapper {
    fun toEntity(company: Company, customer: Customer, request : RegisterContractReq) : Contract {
        return Contract(
            company,
            customer.customerId,
            customer.nickName,
            customer.phoneNumber,
            request.specialty,
            request.purpose,
            request.requestSolution,
            request.description
        )
    }

    fun apply(contract: Contract, request : ApplyContractReq){
        contract.apply(
            request.advanceDeposit,
            request.pee,
            request.startPeriod,
            request.endPeriod)
    }

    fun updateContent(contract: Contract, request : RegisterContractContentReq){
        contract.updateContent(
            ContractContent(
                request.incidentLocation,
                request.incidentTime)
        )
    }

}