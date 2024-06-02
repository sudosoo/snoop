package com.api.pladder.application.service.contract

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.service.contract.manager.ContractManager
import com.api.pladder.application.service.contract.reader.ContractReader
import org.springframework.stereotype.Service

@Service
class ContractService (
    private val manager: ContractManager,
    private val reader: ContractReader
) {
    fun register(req : RegisterContractReq){

    }
}