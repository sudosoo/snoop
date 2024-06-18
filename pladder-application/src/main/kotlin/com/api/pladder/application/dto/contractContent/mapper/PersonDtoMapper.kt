package com.api.pladder.application.dto.contractContent.mapper

import com.api.pladder.application.dto.contractContent.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contractContent.person.request.UpdatePersonReq
import com.api.pladder.core.utils.entity.EntityUpdateUtil
import com.api.pladder.domain.entity.contract.PersonRecord
import java.util.*

object PersonDtoMapper {
    fun toEntity(req: RegisterPersonReq): PersonRecord {
        return PersonRecord(
            UUID.fromString(req.contractId),
            req.status,
            req.name,
            req.gender,
            req.age,
            req.relationship,
            req.workplaceAddr,
            req.impression,
            req.residenceAddr,

        )
    }
    fun update(person: PersonRecord ,req: UpdatePersonReq) {
        return EntityUpdateUtil.updateEntity(person, req)
    }

}