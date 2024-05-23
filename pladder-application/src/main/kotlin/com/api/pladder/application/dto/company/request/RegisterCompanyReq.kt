package com.api.pladder.application.dto.company.request

import com.api.pladder.domain.entity.company.enums.ConfirmStatus
import com.api.pladder.domain.entity.contract.Contract
import jakarta.persistence.CascadeType
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.OneToMany
import java.util.*

class RegisterCompanyReq (
    val companyName: String,
    val bossId: UUID,
    val phoneNumber: String,
    val addr: String,
    val specialization: String,
    val introduction: String
){


}


private val companyName: String? = null
private val bossId: UUID? = null
private val phoneNumber: String? = null
private val address: String? = null
private val specialization: String? = null
private val introduction: String? = null

@OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "company")
private val contracts: List<Contract> = ArrayList()

@Enumerated(value = EnumType.STRING)
private val confirmStatus = ConfirmStatus.WAIT_TING