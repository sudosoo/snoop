package com.api.pladder.presentation.controller.perpetrator

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contractContent.perpetrator.RegisterPerpetratorReq
import com.api.pladder.application.dto.contractContent.perpetrator.UpdatePerpetratorReq
import com.api.pladder.application.service.contractContent.perpetrator.PerpetratorService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.perpetrator.ExplainDeletePerpetrator
import com.api.pladder.presentation.anotation.perpetrator.ExplainRegisterAccomplice
import com.api.pladder.presentation.anotation.perpetrator.ExplainRegisterPerpetrator
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@Tag(name = "가해자", description = "가해자 관련 API")
@RequestMapping("/api/detective/perpetrator")
class PerpetratorController(
    val service : PerpetratorService
): AuthDataProvider, ResponseEntityCreation {

    @PostMapping(value = [])
    @ExplainRegisterPerpetrator
    fun register(request : RegisterPerpetratorReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }

    @PutMapping(value = ["/accomplice"])
    @ExplainRegisterAccomplice
    fun appendAccomplice(perpetratorId: UUID,request : RegisterPerpetratorReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.appendAccomplice(perpetratorId,request))
    }

    @PutMapping(value = ["/update"])
    @ExplainRegisterAccomplice
    fun update(request : UpdatePerpetratorReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.update(request))
    }

    @DeleteMapping()
    @ExplainDeletePerpetrator
    fun delete(perpetratorId: UUID) : ResponseEntity<BaseResp> {
        return getRespEntity(service.delete(perpetratorId))
    }


}