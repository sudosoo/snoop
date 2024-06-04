package com.api.pladder.application.dto.contractContent.perpetrator

import com.api.pladder.domain.entity.contract.enums.Gender

class RegisterPerpetratorReq (
    val contractId : String,
    val name : String,
    val gender : Gender,
    val age : Int,
    val relationship : String,
    val workplaceAddr : String,
    val impression : String,
    val residenceAddr : String,
    val accompliceId : String,
)