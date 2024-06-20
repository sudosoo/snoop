package com.api.pladder.presentation.controller.evidence

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.evidence.RegisterEvidenceReq
import com.api.pladder.application.service.evidence.EvidenceService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.evidence.ExplainGetEvidenceContents
import com.api.pladder.presentation.anotation.evidence.ExplainRegisterEvidence
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "증거 목록", description ="증거 목록 첨부 관련 API")
@RestController
@RequestMapping("/api/detective/evidence")
class EvidenceController (
    val service : EvidenceService
) : AuthDataProvider, ResponseEntityCreation {


    @PostMapping(value = [])
    @ExplainRegisterEvidence
    fun register(request : RegisterEvidenceReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request, getAuthReq()))
    }

    @GetMapping(value = [])
    @ExplainGetEvidenceContents
    fun getContents(
        evidenceId : String,
        @RequestParam(defaultValue = "0") page : Int,
        @RequestParam(defaultValue = "10") size : Int,
                    ) : ResponseEntity<BaseResp> {
        return getRespEntity(service.getContents(evidenceId, getAuthReq(),PageRequest.of(page, size)))
    }


}