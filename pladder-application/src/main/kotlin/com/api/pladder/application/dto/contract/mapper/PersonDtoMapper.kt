package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contract.person.request.UpdatePersonReq
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
    fun updateInfo(person: Person, request: UpdatePersonReq):Person {
        if(request.name != null) person.name = request.name
        if(request.age != 0) person.age = request.age
        if(request.gender != null) person.gender = request.gender
        if(request.relationship != null) person.relationship = request.relationship
        if(request.workplaceAddr != null) person.workplaceAddr = request.workplaceAddr
        if(request.impression != null) person.impression = request.impression
        if(request.residenceAddr != null) person.residenceAddr = request.residenceAddr

        return person
    }


}