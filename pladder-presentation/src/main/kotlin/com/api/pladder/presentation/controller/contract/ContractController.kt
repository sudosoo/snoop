package com.api.pladder.presentation.controller.contract

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.ApplyContractContentReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.contract.ExplainGetContract
import com.api.pladder.presentation.anotation.contract.ExplainRegisterContract
import com.api.pladder.presentation.anotation.contractContent.ExplainUpdateContractContent
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

    @ExplainUpdateContractContent
    @PutMapping(value = ["/update"])
    fun updateContent(request: ApplyContractContentReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.updateContent(request))
    }

    @ExplainGetContract
    @GetMapping(value = ["/status"])
    fun findStatus() : ResponseEntity<BaseResp>{
        return getRespEntity(service.countStatus(getAuthReq()))
    }
    @ExplainGetContract
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

    @ExplainGetContract
    @PatchMapping(value = ["/accept"])
    fun accept(contractId : String) : ResponseEntity<BaseResp>{
        return getRespEntity(service.accept(contractId))
    }






}