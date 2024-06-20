package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contract.person.request.UpdatePersonReq
import com.api.pladder.core.utils.entity.EntityUpdateUtil
import com.api.pladder.domain.entity.contract.Person
import java.util.*

object PersonDtoMapper {
    fun toEntity(req: RegisterPersonReq): Person {
        return Person(
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
    fun update(person: Person, req: UpdatePersonReq) {
        return EntityUpdateUtil.updateEntity(person, req)
    }

}