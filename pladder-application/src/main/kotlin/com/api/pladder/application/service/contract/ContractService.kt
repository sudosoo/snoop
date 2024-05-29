package com.api.pladder.application.service.contract

import com.api.pladder.application.dto.contract.request.ContractRegisterReq
import com.api.pladder.application.service.contract.manager.ContractManager
import com.api.pladder.application.service.contract.reader.ContractReader
import org.springframework.stereotype.Service

@Service
class ContractService (
    private val contractManager: ContractManager,
    private val contractReader: ContractReader
) {

    fun register(req : ContractRegisterReq){

    }
}