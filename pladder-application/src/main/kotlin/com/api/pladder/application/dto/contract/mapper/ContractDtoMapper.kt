package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import java.util.*

object ContractDtoMapper {

    fun ContractToEntity(req : RegisterContractReq, company: Company) : Contract {
        return Contract(
            UUID.fromString(req.clientId),
            company,
            req.advanceDeposit,
            req.pee,
            req.purpose,
            req.requestSolution
        );
    }

}