package com.api.pladder.application.dto.contract.response

import com.api.pladder.domain.entity.contract.Contract

class GetContractListResp(contract : Contract){
    val contractId = contract.contractId
    val customerId = contract.customerId
    val applyDate = contract.applyDate
    val specialty = contract.specialty
    val purpose = contract.purpose
    val pee = contract.pee
    val client = contract.clientName
    val phoneNumber = contract.clientPhone
}