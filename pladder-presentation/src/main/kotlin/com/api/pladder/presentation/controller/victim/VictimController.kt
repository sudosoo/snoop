package com.api.pladder.presentation.controller.victim

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.contractContent.victim.RegisterVictimReq
import com.api.pladder.application.dto.contractContent.victim.UpdateVictimReq
import com.api.pladder.application.service.contractContent.victim.VictimService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.victim.ExplainGetVictim
import com.api.pladder.presentation.anotation.victim.ExplainRegisterVictim
import com.api.pladder.presentation.anotation.victim.ExplainUpdateVictim
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "피해자", description = "피해자 관련 API")
@RequestMapping("/api/detective/victim")
class VictimController(
    val service : VictimService
): AuthDataProvider, ResponseEntityCreation {

    @ExplainRegisterVictim
    @PostMapping(value = [])
    fun register(req : RegisterVictimReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.register(req))
    }

    @ExplainUpdateVictim
    @PostMapping(value = ["/{victimId}"])
    fun update(@PathVariable victimId: String, req : UpdateVictimReq) : ResponseEntity<BaseResp> {
        return getRespEntity(service.update(victimId,req))
    }
    @ExplainGetVictim
    @GetMapping(value = ["/{contractId}"])
    fun find(@PathVariable contractId : String ) : ResponseEntity<BaseResp> {
        return getRespEntity(service.findById(contractId))
    }

}