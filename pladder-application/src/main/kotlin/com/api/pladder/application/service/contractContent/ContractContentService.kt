package com.api.pladder.application.service.contractContent

import com.api.pladder.application.dto.contractContent.request.RegisterContractContentReq
import com.api.pladder.application.service.contractContent.manager.ContractContentManager
import com.api.pladder.application.service.contractContent.reader.ContractContentReader
import org.springframework.stereotype.Service

@Service
class ContractContentService (
    val manager: ContractContentManager,
    val reader: ContractContentReader
){
    fun register(req : RegisterContractContentReq){

        manager.register(req)
    }

}