package com.api.pladder.application.dto.company.mapper

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.domain.entity.company.Company
import java.util.*

object CompanyDtoMapper {

    fun companyToEntity(request: RegisterCompanyReq, detectiveId: UUID) : Company {
        return Company(
            request.name,
            request.addr,
            request.phoneNumber,
            request.introduction,
            request.specialization,
            detectiveId)
    }

    fun updateInfo(company: Company, request: UpdateCompanyInfoReq){
        company.updateInfo(request.introduction,request.specialization)
    }


}