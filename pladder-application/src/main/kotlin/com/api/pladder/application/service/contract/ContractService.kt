package com.api.pladder.application.service.contract

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contractContent.response.FindStatusContractResp
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.application.service.contract.manager.ContractManager
import com.api.pladder.application.service.contract.reader.ContractReader
import com.api.pladder.core.obj.AuthUserObject
import org.springframework.stereotype.Service

@Service
class ContractService (
    private val manager: ContractManager,
    private val reader: ContractReader,
    private val companyService: CompanyService,
) {
    fun register(req : RegisterContractReq , authObj : AuthUserObject){
        val company = companyService.reader.getInstance(req.companyId)
        manager.register(req,company)
    }

    fun findStatus(req : AuthUserObject) : FindStatusContractResp {
        val contracts = reader.findAllById(req.userId)
        return FindStatusContractResp().toResp(contracts)

    }

}