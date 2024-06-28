package com.api.pladder.presentation.controller.evidence

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.evidence.RegisterEvidenceReq
import com.api.pladder.application.service.evidence.EvidenceService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.core.utils.securityProvider.AuthDataProvider.Companion.PAGE_SIZE
import com.api.pladder.presentation.anotation.evidence.ExplainDeleteEvidence
import com.api.pladder.presentation.anotation.evidence.ExplainGetEvidenceContents
import com.api.pladder.presentation.anotation.evidence.ExplainRegisterEvidence
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "증거 목록", description ="증거 목록 첨부 관련 API")
@RestController
@RequestMapping("/api")
class EvidenceController (
    val service : EvidenceService
) : AuthDataProvider, ResponseEntityCreation {

    @PostMapping(value = ["/detective/evidence"])
    @ExplainRegisterEvidence
    fun register(request : RegisterEvidenceReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request, getAuthReq()))
    }

    @GetMapping(value = ["/detective/evidence","/customer/evidence"])
    @ExplainGetEvidenceContents
    fun getContents(
        @RequestParam evidenceId : String,
        @RequestParam(defaultValue = "0") page : Int,
        ) : ResponseEntity<BaseResp> {
        return getRespEntity(service.getContents(evidenceId,PageRequest.of(page, PAGE_SIZE)))
    }

    @GetMapping(value = ["/detective/evidence"])
    @ExplainDeleteEvidence
    fun delete(
        @RequestParam evidenceId : String,
        @RequestParam(defaultValue = "0") page : Int,
    ) : ResponseEntity<BaseResp> {
        return getRespEntity(service.delete(evidenceId, getAuthReq()))
    }


}