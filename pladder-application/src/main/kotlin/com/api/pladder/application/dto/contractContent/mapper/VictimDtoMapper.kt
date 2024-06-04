package com.api.pladder.application.dto.contractContent.mapper

import com.api.pladder.application.dto.contractContent.victim.RegisterVictimReq
import com.api.pladder.domain.entity.contract.Victim
import java.util.*

object VictimDtoMapper {
    fun toEntity(req: RegisterVictimReq): Victim {
        return Victim(
            UUID.fromString(req.contractId),
            req.name,req.relationship,req.phoneNumber)
    }

}
