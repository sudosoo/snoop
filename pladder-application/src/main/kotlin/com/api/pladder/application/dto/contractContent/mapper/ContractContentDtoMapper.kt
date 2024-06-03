package com.api.pladder.application.dto.contractContent.mapper

import com.api.pladder.application.dto.contractContent.request.RegisterContractContentReq
import com.api.pladder.domain.entity.contract.ContractContent

object ContractContentDtoMapper {
    fun contractContentToEntity(req : RegisterContractContentReq) : ContractContent{
        return ContractContent(req.contractId,req.)

    }
}