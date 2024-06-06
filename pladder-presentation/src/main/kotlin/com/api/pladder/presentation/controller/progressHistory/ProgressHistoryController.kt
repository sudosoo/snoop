package com.api.pladder.presentation.controller.progressHistory

import com.api.pladder.application.dto.common.BaseListRespV2
import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryRegisterReq
import com.api.pladder.application.service.progressHistory.ProgressHistoryService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.anotation.progressHistory.ExplainDeleteProgressHistory
import com.api.pladder.presentation.anotation.progressHistory.ExplainRegisterProgressHistory
import com.api.pladder.presentation.anotation.progressHistory.ExplainUpdateProgressHistory
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "사건 진행 상황", description ="사건 진행 상황 관련 API")
@RestController
@RequestMapping("/api")
class ProgressHistoryController (
    val service : ProgressHistoryService
) : AuthDataProvider, ResponseEntityCreation {

    val PAGE_SIZE = 10

    @ExplainRegisterProgressHistory
    @PostMapping(value = ["/detective"])
    fun register(req : ProgressHistoryRegisterReq) : ResponseEntity<BaseResp>{
        return getRespEntity(service.registerProgress(req))
    }
    @ExplainUpdateProgressHistory
    @PutMapping(value = ["/detective"])
    fun updateContent(req : ProgressHistoryRegisterReq) : ResponseEntity<BaseResp>{
        return getRespEntity(service.registerProgress(req))
    }

    @GetMapping(value = ["/detective/","/customer"])
    fun getHistories(contractId : String,
                     @RequestParam(defaultValue = "0") page : Int,
                     ) : ResponseEntity<BaseListRespV2>{
        return getListRespEntity(service.getProgressHistories(contractId, PageRequest.of(page, PAGE_SIZE)))
    }

    @ExplainDeleteProgressHistory
    @DeleteMapping(value = ["/detective/{progressId}"])
    fun delete(@PathVariable progressId : String) : ResponseEntity<BaseResp>{
        return getRespEntity(service.deleteProgress(progressId))
    }




}