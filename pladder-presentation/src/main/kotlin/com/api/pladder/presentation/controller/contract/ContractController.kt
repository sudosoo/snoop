package com.api.pladder.presentation.controller.contract

import com.api.pladder.application.dto.common.BaseListRespV2
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contract.request.RegisterSignReq
import com.api.pladder.application.dto.contract.request.SuggestContractReq
import com.api.pladder.application.dto.contract.request.UpdateContractContentReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.core.utils.securityProvider.AuthDataProvider.Companion.PAGE_SIZE
import com.api.pladder.presentation.anotation.contract.*
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "계약서", description = "계약서 관련 API")
@RequestMapping("/api")
class ContractController(
    val service: ContractService
) : ResponseEntityCreation, AuthDataProvider {

    @ExplainRegisterContract
    @PostMapping(value = ["/customer/contract"])
    fun register(request: RegisterContractReq): ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request, getAuthReq()))
    }

    @ExplainGetContract
    @GetMapping(value = ["/detective/contract/status"])
    fun findStatus(): ResponseEntity<BaseResp> {
        return getRespEntity(service.countStatus(getAuthReq()))
    }

    @ExplainGetContractList
    @GetMapping(value = ["/detective/contract/getList"])
    fun getListByStatus(
        @RequestParam(name = "신청서,결제 대기,진행중") status : String,
        @RequestParam(defaultValue = "0"
        ) page: Int
    ): ResponseEntity<BaseListRespV2> {
        return getListRespEntity(service.getListByStatus(status,getAuthReq(), PageRequest.of(page, PAGE_SIZE)))
    }

    @ExplainGetContract
    @GetMapping(value = ["/detective/contract/getDetail"])
    fun getDetail(@RequestParam contractId: String): ResponseEntity<BaseResp> {
        return getRespEntity(service.getDetail(getAuthReq(), contractId))
    }

    @ExplainApplyContract
    @PutMapping(value = ["/detective/contract/suggest"])
    fun suggest(request: SuggestContractReq): ResponseEntity<BaseResp> {
        return getRespEntity(service.suggest(request,getAuthReq()))
    }

    @ExplainApplyContract
    @PutMapping(value = ["/consumer/contract/apply"])
    fun apply(@RequestParam contractId: String): ResponseEntity<BaseResp> {
        return getRespEntity(service.apply(contractId,getAuthReq()))
    }

    @ExplainUpdateContractContent
    @PutMapping(value = ["/detective/contract/updateContent"])
    fun updateContent(request: UpdateContractContentReq): ResponseEntity<BaseResp> {
        return getRespEntity(service.updateContent(request,getAuthReq()))
    }

    @ExplainUpdateContractContent
    @DeleteMapping(value = ["/detective/contract, /customer/contract"])
    fun delete(@RequestParam contractId: String): ResponseEntity<BaseResp> {
        return getRespEntity(service.delete(contractId,getAuthReq()))
    }

    @ExplainUploadContractSign
    @PutMapping(value = ["/customer/contract/sign", "/detective/contract/sign"])
    fun uploadSign(request: RegisterSignReq): ResponseEntity<BaseResp> {
        return getRespEntity(service.uploadSign(request, getAuthReq()))
    }

    @ExplainGetContractSign
    @GetMapping(value = ["/customer/contract/sign", "/detective/contract/sign"])
    fun getSign(@RequestParam contractId: String): ResponseEntity<BaseResp> {
        return getRespEntity(service.getSign(contractId,getAuthReq()))
    }


}