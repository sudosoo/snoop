package com.api.pladder.application.dto.contractContent.perpetrator

import com.api.pladder.domain.entity.contract.enums.Gender

class UpdatePerpetratorReq (
    val perpetratorId: String,
    val name: String,
    val age: Int,
    val gender : Gender,
    val relationship: String,
    val workplaceAddr: String,
    val impression: String,
    val residenceAddr: String,
    )
