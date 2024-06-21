package com.api.pladder.application.dto.company.response

import com.api.pladder.domain.entity.company.Company

class CompanyDefaultResp(
   company : Company
) {
    val id = company.companyId
    val name = company.companyName
}