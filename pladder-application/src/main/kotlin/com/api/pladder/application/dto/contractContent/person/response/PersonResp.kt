package com.api.pladder.application.dto.contractContent.person.response

import com.api.pladder.domain.entity.contract.PersonRecord

class PersonResp ( model: PersonRecord){
    val id: String? = model.id.toString()
    val contractId: String? = model.contractId.toString()
    val name : String? = model.name
}