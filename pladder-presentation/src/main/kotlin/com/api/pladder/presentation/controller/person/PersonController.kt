package com.api.pladder.presentation.controller.person

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contractContent.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contractContent.person.request.UpdatePersonReq
import com.api.pladder.application.service.contractContent.personRecords.PersonService
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
class PersonController(
    val service : PersonService
): AuthDataProvider, ResponseEntityCreation {

    @PostMapping(value = [])
    @ExplainRegisterPerpetrator
    fun register(request : RegisterPersonReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }

    @PutMapping(value = ["/accomplice"])
    @ExplainRegisterAccomplice
    fun appendAccomplice(perpetratorId: UUID,request : RegisterPersonReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.appendAccomplice(perpetratorId,request))
    }

    @PutMapping(value = ["/update"])
    @ExplainRegisterAccomplice
    fun update(request : UpdatePersonReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.update(request))
    }

    @DeleteMapping()
    @ExplainDeletePerpetrator
    fun delete(perpetratorId: UUID) : ResponseEntity<BaseResp> {
        return getRespEntity(service.delete(perpetratorId))
    }


}