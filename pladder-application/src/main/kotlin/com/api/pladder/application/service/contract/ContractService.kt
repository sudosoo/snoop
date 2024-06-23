package com.api.pladder.application.service.contract

import com.api.pladder.application.dto.contract.mapper.ContractDtoMapper
import com.api.pladder.application.dto.contract.request.ApplyContractReq
import com.api.pladder.application.dto.contract.request.RegisterContractContentReq
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.RegisterSignReq
import com.api.pladder.application.dto.contract.response.ContractDetailResp
import com.api.pladder.application.dto.contract.response.CountContractStatusResp
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.application.service.contract.manager.ContractManager
import com.api.pladder.application.service.contract.reader.ContractReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContractService (
    private val manager: ContractManager,
    private val reader: ContractReader,
    private val companyService: CompanyService,
    private val customerService: CustomerService,
    private val fileService: FileService
) {
    fun register(request : RegisterContractReq , authObj : AuthUserObject){
        if (authObj.userType == UserType.DETECTIVE){
            throw AccessDeniedException("해당 계약을 등록할 권한이 없습니다.")
        }
        val company = companyService.reader.getInstance(request.companyId)
        val customer = customerService.reader.findById(authObj.userId!!)
        manager.register(request,company,customer)
    }


    fun apply(request : ApplyContractReq){
        val contract = reader.findById(UUID.fromString(request.contractId))
        ContractDtoMapper.apply(contract,request)
        manager.save(contract)
    }

    fun countStatus(request : AuthUserObject) : CountContractStatusResp {
        val contracts = reader.findAllById(request.userId!!)
        return CountContractStatusResp().toResp(contracts)
    }
    fun getContractList(request : AuthUserObject,pageReq : PageRequest): List<ContractDetailResp> {
        val company = companyService.reader.getInstanceByDetectiveId(request.userId!!)
        val contracts: List<Contract> = reader.findWaitingContractByCompany(company)
        return contracts.map { ContractDetailResp(it) }
    }


    fun getContractDetail(request : AuthUserObject ,contractId : String): ContractDetailResp {
        val contract = reader.findById(UUID.fromString(contractId))
        if (contract.company.detectiveId != request.userId && request.userType != UserType.ADMIN){
            throw AccessDeniedException("해당 계약에 대한 접근 권한이 없습니다.")
        }
        return ContractDetailResp(contract)
    }

    fun findById(contractId : UUID) : Contract = reader.findById(contractId)

    fun updateContent(request : RegisterContractContentReq){
        manager.updateContent(request)
    }

    fun delete(contractId : UUID){
            manager.deleteById(contractId)
    }
    fun uploadSign(request: RegisterSignReq,authObj: AuthUserObject){
        val contract = reader.findById(UUID.fromString(request.contractId))
        fileService.save(FileRequest(
            type = FileType.SIGN,
            file = request.image,
            targetId = contract.contractId,
            targetType = FileTargetType.CONTRACT,
            writerId = authObj.userId!!
        ))
    }

}