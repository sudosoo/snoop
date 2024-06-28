package com.api.pladder.application.dto.company.mapper

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.user.Detective

object CompanyDtoMapper {

    fun toEntity(request: RegisterCompanyReq, detective: Detective) : Company {
        return Company(
            request.name,
            request.addr,
            request.phoneNumber,
            request.introduction,
            request.specialization,
            detective
        )
    }

    fun updateInfo(company: Company, request: UpdateCompanyInfoReq) : Company {
        if (!request.introduction.isNullOrBlank()) company.introduction = request.introduction
        if (!request.specialization.isEmpty()) company.specialization = request.specialization
        return company
    }

}