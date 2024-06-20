package com.api.pladder.application.dto.contract.person.response

import com.api.pladder.domain.entity.contract.Person

class PersonResp ( model: Person){
    val id: String = model.id.toString()
    val contractId: String = model.contractId.toString()
    val name : String = model.name
}