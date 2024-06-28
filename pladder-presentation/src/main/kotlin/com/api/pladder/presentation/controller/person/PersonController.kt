package com.api.pladder.presentation.controller.person

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contract.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contract.person.request.UpdatePersonReq
import com.api.pladder.application.service.contract.person.PersonService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.person.ExplainDeletePerson
import com.api.pladder.presentation.anotation.person.ExplainRegisterAccomplice
import com.api.pladder.presentation.anotation.person.ExplainRegisterPerson
import com.api.pladder.presentation.anotation.person.ExplainUpdatePerson
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@Tag(name = "가해자", description = "가해자 관련 API")
@RequestMapping("/api/detective/person")
class PersonController(
    val service : PersonService
): AuthDataProvider, ResponseEntityCreation {

    @PostMapping(value = [])
    @ExplainRegisterPerson
    fun register(request : RegisterPersonReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }

    @PutMapping(value = ["/accomplice"])
    @ExplainRegisterAccomplice
    fun appendAccomplice(@RequestParam perpetratorId: UUID,@RequestBody request : RegisterPersonReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.appendAccomplice(perpetratorId,request))
    }

    @PutMapping(value = ["/update"])
    @ExplainUpdatePerson
    fun update(request : UpdatePersonReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.update(request))
    }

    @DeleteMapping()
    @ExplainDeletePerson
    fun delete(@RequestParam personId: String) : ResponseEntity<BaseResp> {
        return getRespEntity(service.delete(personId))
    }


}