package com.api.pladder.application.dto.company.response

import com.api.pladder.domain.entity.company.Company

class UpdateCompanyResp(company: Company) {
    val id: String = company.companyId.toString()
    val introduce: String = company.introduction
    val specialization: List<String> = company.specialization.map { it.name }
}