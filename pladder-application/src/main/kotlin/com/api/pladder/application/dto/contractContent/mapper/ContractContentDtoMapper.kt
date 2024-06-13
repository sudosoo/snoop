package com.api.pladder.application.dto.contractContent.mapper

import com.api.pladder.application.dto.contractContent.request.RegisterContractContentReq
import com.api.pladder.application.dto.contractContent.request.UpdateContractContentReq
import com.api.pladder.domain.entity.contract.ContractContent

object ContractContentDtoMapper {
    fun toEntity(req : RegisterContractContentReq) : ContractContent{
        return ContractContent(req.contractId,req.contractField,req.incidentLocation,req.incidentTime) }

    fun update(contractContent :ContractContent, req : UpdateContractContentReq){
        contractContent.update(req.contractField,req.incidentLocation,req.incidentTime)
    }

}