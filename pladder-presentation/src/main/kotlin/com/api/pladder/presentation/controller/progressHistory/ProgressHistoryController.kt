package com.api.pladder.presentation.controller.progressHistory

import com.api.pladder.application.dto.common.BaseListRespV2
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.progressHistory.request.ProgressContentRegisterReq
import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryUpdateReq
import com.api.pladder.application.service.progressHistory.ProgressHistoryService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.core.utils.securityProvider.AuthDataProvider.Companion.PAGE_SIZE
import com.api.pladder.presentation.anotation.progressHistory.ExplainDeleteProgressHistory
import com.api.pladder.presentation.anotation.progressHistory.ExplainRegisterProgressHistory
import com.api.pladder.presentation.anotation.progressHistory.ExplainUpdateProgressHistory
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Tag(name = "사건 진행 상황", description ="사건 진행 상황 관련 API")
class ProgressHistoryController (
    val service : ProgressHistoryService
) : AuthDataProvider, ResponseEntityCreation {


    @ExplainRegisterProgressHistory
    @PostMapping(value = ["/detective/progress"])
    fun register(
        @ModelAttribute
        request : ProgressContentRegisterReq,
    ) : ResponseEntity<BaseResp>{
        return getRespEntity(service.register(request))
    }


    @ExplainUpdateProgressHistory
    @PutMapping(value = ["/detective/progress"])
    fun updateContent(request : ProgressHistoryUpdateReq) : ResponseEntity<BaseResp>{
        return getRespEntity(service.updateContent(request))
    }

    @GetMapping(value = ["/open/progress"])
    fun getHistories(contractId : String,
                     @RequestParam(defaultValue = "0") page : Int,
                     ) : ResponseEntity<BaseListRespV2>{
        return getListRespEntity(service.getHistories(contractId, PageRequest.of(page, PAGE_SIZE),getAuthReq()))
    }

    @ExplainDeleteProgressHistory
    @DeleteMapping(value = ["/detective/progress"])
    fun delete(@RequestParam progressId : String) : ResponseEntity<BaseResp>{
        return getRespEntity(service.delete(progressId))
    }




}