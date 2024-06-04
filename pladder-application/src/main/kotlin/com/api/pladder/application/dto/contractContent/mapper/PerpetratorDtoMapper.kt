package com.api.pladder.application.dto.contractContent.mapper

import com.api.pladder.application.dto.contractContent.perpetrator.RegisterPerpetratorReq
import com.api.pladder.domain.entity.contract.Perpetrator
import java.util.*

object PerpetratorDtoMapper {
    fun toEntity(req: RegisterPerpetratorReq): Perpetrator {
        return Perpetrator(
            UUID.fromString(req.contractId),
            req.name,
            req.gender,
            req.age,
            req.relationship,
            req.workplaceAddr,
            req.impression,
            req.residenceAddr
        )
    }
}