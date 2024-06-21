package com.api.pladder.presentation.controller.contract

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.request.ApplyContractReq
import com.api.pladder.application.dto.contract.request.RegisterContractContentReq
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.contract.*
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "계약서", description = "계약서 관련 API")
@RequestMapping("/api/detective/contract")
class ContractController (
    val service: ContractService
) : ResponseEntityCreation, AuthDataProvider {

    @ExplainRegisterContract
    @PostMapping(value = [])
    fun register(request: RegisterContractReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request, getAuthReq()))
    }

    @ExplainGetContract
    @GetMapping(value = ["/status"])
    fun findStatus() : ResponseEntity<BaseResp>{
        return getRespEntity(service.countStatus(getAuthReq()))
    }

    @ExplainGetContractList
    @GetMapping(value = ["/getList"])
    fun getList(
        @RequestParam(defaultValue = "0") page : Int,
        @RequestParam(defaultValue = "10") size : Int,
    ) : ResponseEntity<BaseResp>{
        return getRespEntity(service.getContractList(getAuthReq(), PageRequest.of(page, size)))
    }

    @ExplainGetContract
    @GetMapping(value = ["/getDetail"])
    fun getDetail(contractId : String) : ResponseEntity<BaseResp>{
        return getRespEntity(service.getContractDetail(getAuthReq(),contractId))
    }

    @ExplainApplyContract
    @PutMapping(value = ["/apply"])
    fun apply(request: ApplyContractReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.apply(request))
    }


    @ExplainUpdateContractContent
    @PutMapping(value = ["/updateContent"])
    fun updateContent(request : RegisterContractContentReq) : ResponseEntity<BaseResp>{
        return getRespEntity(service.updateContent(request))
    }







}