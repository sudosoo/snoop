package com.api.pladder.presentation.controller.victim

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contractContent.victim.RegisterVictimReq
import com.api.pladder.application.service.contractContent.victim.VictimService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.anotation.user.ExplainRegisterUser
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "피해자", description = "피해자 관련 API")
@RequestMapping("/api/detective/victim")
class VictimController(
    val service : VictimService
): AuthDataProvider, ResponseEntityCreation {

    @ExplainRegisterVictim
    @PostMapping(value = [])
    fun register(request : RegisterVictimReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }

    @ExplainUpdateVictim
    @PostMapping(value = [])
    fun update(request : RegisterVictimReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }
    @ExplainGetVictim
    @GetMapping(value = [])
    fun find(request : RegisterVictimReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(request))
    }

}