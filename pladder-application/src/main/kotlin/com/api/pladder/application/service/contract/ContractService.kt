package com.api.pladder.application.service.contract

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.response.ContractDetailResp
import com.api.pladder.application.dto.contract.response.GetContractListResp
import com.api.pladder.application.dto.contractContent.response.FindStatusContractResp
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.application.service.contract.manager.ContractManager
import com.api.pladder.application.service.contract.reader.ContractReader
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.contract.Contract
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContractService (
    private val manager: ContractManager,
    private val reader: ContractReader,
    private val companyService: CompanyService,
    private val customerService: CustomerService
) {
    fun register(req : RegisterContractReq , authObj : AuthUserObject){
        if (authObj.userType == UserType.DETECTIVE){
            throw AccessDeniedException("해당 계약을 등록할 권한이 없습니다.")
        }
        val company = companyService.reader.getInstance(req.companyId)
        val customer = customerService.reader.findById(authObj.userId!!)
        manager.register(req,company,customer)
    }

    fun findStatus(req : AuthUserObject) : FindStatusContractResp {
        val contracts = reader.findAllById(req.userId!!)
        return FindStatusContractResp().toResp(contracts)
    }
    fun getContractList(req : AuthUserObject): List<GetContractListResp> {
        val company = companyService.reader.getInstanceByDetectiveId(req.userId!!)
        val contracts: List<Contract> = reader.findWaitingContractByCompany(company)
        return contracts.map { GetContractListResp(it) }
    }

    fun getContractDetail(req : AuthUserObject ,contractId : String): ContractDetailResp {
        val contract = reader.findById(UUID.fromString(contractId))
        if (contract.company.detectiveId != req.userId && req.userType != UserType.ADMIN){
            throw AccessDeniedException("해당 계약에 대한 접근 권한이 없습니다.")
        }
        return ContractDetailResp(contract)

    }

    fun findById(contractId : UUID) : Contract = reader.findById(contractId)

    fun validateOwner(contractId: UUID, authObj: AuthUserObject) {
        val contract = reader.findById(contractId)
        if (contract.company.detectiveId != authObj.userId){
            throw AccessDeniedException("올바르지 않은 접근입니다.")
        }
    }

}