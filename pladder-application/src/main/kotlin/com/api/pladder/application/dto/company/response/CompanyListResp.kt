package com.api.pladder.application.dto.company.response

import com.api.pladder.domain.entity.company.Company

class CompanyListResp(
   company : Company
) {
    val id = company.companyId
    val name = company.companyName
    val address = company.address
    val specification = company.specialization
    val introduction = company.introduction
}
