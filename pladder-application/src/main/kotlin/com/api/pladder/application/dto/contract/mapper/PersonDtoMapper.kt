package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contract.person.request.UpdatePersonReq
import com.api.pladder.core.utils.entity.EntityUpdateUtil
import com.api.pladder.domain.entity.contract.Person
import java.util.*

object PersonDtoMapper {
    fun toEntity(request: RegisterPersonReq): Person {
        return Person(
            UUID.fromString(request.contractId),
            request.status,
            request.name,
            request.gender,
            request.age,
            request.relationship,
            request.workplaceAddr,
            request.impression,
            request.residenceAddr,

            )
    }
    fun update(person: Person, request: UpdatePersonReq) {
        return EntityUpdateUtil.updateEntity(person, request)
    }

}