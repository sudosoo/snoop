package com.api.pladder.presentation.controller.contractContent

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.contractContent.ContractContentService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.anotation.contract.ExplainRegisterContract
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "계약서", description = "계약서 관련 API")
@RequestMapping("/api")
class ContractContentController (
    val service: ContractContentService
) : ResponseEntityCreation, AuthDataProvider {

    @ExplainRegisterContract
    @PostMapping(value = ["/detective/contract"])
    fun register(request: RegisterContractReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request, getAuthReq()))
    }

}