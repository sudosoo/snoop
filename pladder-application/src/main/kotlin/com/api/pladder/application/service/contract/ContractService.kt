package com.api.pladder.application.service.contract

import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.RegisterSignReq
import com.api.pladder.application.dto.contract.request.SuggestContractReq
import com.api.pladder.application.dto.contract.request.UpdateContractContentReq
import com.api.pladder.application.dto.contract.response.ContractDetailResp
import com.api.pladder.application.dto.contract.response.CountContractStatusResp
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.dto.file.response.SignResp
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.application.service.contract.manager.ContractManager
import com.api.pladder.application.service.contract.reader.ContractReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.application.service.user.detective.DetectiveService
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.file.FileUtils
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.contract.enums.ContractStatus
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContractService(
    private val manager: ContractManager,
    private val reader: ContractReader,
    private val companyService: CompanyService,
    private val detectiveService: DetectiveService,
    private val customerService: CustomerService,
    private val fileService: FileService,
    private val fileUtils: FileUtils
) {
    fun register(request: RegisterContractReq, authObj: AuthUserObject) {
        val company = companyService.reader.getInstance(request.companyId)
        val customer = customerService.reader.findById(authObj.userId!!)
        manager.register(request, company, customer)
    }

    fun suggest(request: SuggestContractReq, authObj: AuthUserObject) {
        val contract = reader.findById(UUID.fromString(request.contractId))
        if (authObj.userType != UserType.ADMIN) {
            if (contract.customerId == authObj.userId!!) {
                throw AccessDeniedException("잘못된 접근입니다.")
            }
        }

        manager.updateAndSave(contract, request)
    }

    fun apply(contractId: String, authObj: AuthUserObject) {
        val contract = reader.findById(UUID.fromString(contractId))
        if (authObj.userType != UserType.ADMIN) {
            if (contract.company.detective.detectiveId != authObj.userId!!) {
                throw AccessDeniedException("잘못된 접근입니다.")
            }
        }
        contract.updateOngoing()
        manager.save(contract)
    }

    fun countStatus(authObj: AuthUserObject): CountContractStatusResp {
        val contracts = reader.findAllById(authObj.userId!!)
        return CountContractStatusResp(contracts)
    }

    fun getListByStatus(status: String, authObj: AuthUserObject, pageReq: PageRequest): Page<ContractDetailResp> {
        val detective = detectiveService.findById(authObj.userId!!)
        val company = companyService.reader.findById(detective.detectiveId)
        val contracts = reader.findAllByCompanyAndStatus(company, ContractStatus.fromString(status))
        val contentResp = contracts.map { ContractDetailResp(it) }
        return PageImpl(contentResp, pageReq, contracts.size.toLong())
    }

    fun getDetail(authObj: AuthUserObject, contractId: String): ContractDetailResp {
        val contract = reader.findById(UUID.fromString(contractId))
        validateOwner(authObj, contract)
        return ContractDetailResp(contract)
    }



    fun findById(contractId: UUID): Contract = reader.findById(contractId)

    fun updateContent(request: UpdateContractContentReq, authObj: AuthUserObject) {
        val contract = reader.findById(UUID.fromString(request.contractId))
        if (authObj.userType != UserType.ADMIN) {
            if (contract.company.detective.detectiveId != authObj.userId) {
                throw AccessDeniedException("잘못된 접근입니다.");
            }
        }
        manager.updateAndSave(contract, request)
    }

    fun delete(contractId: String , authObj: AuthUserObject) {
        val contract = reader.findById(UUID.fromString(contractId))
        if (authObj.userType != UserType.ADMIN) {
            if (contract.company.detective.detectiveId != authObj.userId && contract.customerId != authObj.userId) {
                throw AccessDeniedException("잘못된 접근입니다.");
            }
        }
        manager.deleteById(UUID.fromString(contractId))
    }

    fun uploadSign(request: RegisterSignReq, authObj: AuthUserObject) {
        val contract = reader.findById(UUID.fromString(request.contractId))
        if (authObj.userType != UserType.ADMIN) {
            if (contract.company.detective.detectiveId != authObj.userId && contract.customerId != authObj.userId) {
                throw AccessDeniedException("잘못된 접근입니다.");
            }
        }
        fileService.save(
            FileRequest(
                type = FileType.SIGN,
                file = request.image,
                targetId = contract.contractId,
                targetType = FileTargetType.CONTRACT,
                writerId = authObj.userId!!,
                userType = authObj.userType
            )
        )
    }

    fun getSign(contractId: String , authObj: AuthUserObject): List<SignResp> {
        val contract = reader.findById(UUID.fromString(contractId))
        if (authObj.userType != UserType.ADMIN) {
            if (contract.company.detective.detectiveId != authObj.userId) {
                throw AccessDeniedException("잘못된 접근입니다.");
            }
        }
        val fileResps = fileService.getPagedFileRespByTargetIdAndTargetType(
            UUID.fromString(contractId),
            FileTargetType.CONTRACT,
            PageRequest.of(0, 10)
        )

        return fileResps.map {
            val userType = fileUtils.getUserTypeFromFileName(it.fileName)
            SignResp(it, userType.toString())
        }
    }

    private fun validateOwner(
        authObj: AuthUserObject,
        contract: Contract
    ) {
        if (authObj.userType != UserType.ADMIN) {
            if (contract.company.detective.detectiveId != authObj.userId!! && contract.customerId != authObj.userId) {
                throw AccessDeniedException("잘못된 접근입니다.")
            }
        }
    }

}