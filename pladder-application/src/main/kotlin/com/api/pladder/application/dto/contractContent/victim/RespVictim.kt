package com.api.pladder.application.dto.contractContent.victim

import com.api.pladder.domain.entity.contract.Victim
import io.swagger.v3.oas.annotations.media.Schema

class RespVictim (victim: Victim){
    @Schema(description="계약서 ID")
    val contractId = victim.contractId.toString()
    @Schema(description="이름")
    val name = victim.name
}