package com.api.pladder.application.dto.company.response

import com.api.pladder.domain.entity.company.Company

class CompanyResp {
    val id: String

    constructor(company: Company) {
        this.id = company.companyId.toString()
    }

    constructor(companyId: String) {
        this.id = companyId
    }
}