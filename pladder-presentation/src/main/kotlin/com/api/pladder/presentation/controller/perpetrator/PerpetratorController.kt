package com.api.pladder.presentation.controller.perpetrator

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contractContent.perpetrator.RegisterPerpetratorReq
import com.api.pladder.application.service.contractContent.perpetrator.PerpetratorService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.anotation.user.ExplainRegisterUser
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@Tag(name = "가해자", description = "가해자 관련 API")
@RequestMapping("/api/detective/victim")
class PerpetratorController(
    val service : PerpetratorService
): AuthDataProvider, ResponseEntityCreation {

    @ExplainRegisterUser
    @PostMapping(value = [])
    fun register(request : RegisterPerpetratorReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }

    @ExplainRegisterUser
    @PostMapping(value = [])
    fun appendAccomplice(perpetratorId: UUID,request : RegisterPerpetratorReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.appendAccomplice(perpetratorId,request))
    }

}