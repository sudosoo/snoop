package com.api.pladder.application.dto.company.mapper

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.domain.entity.company.Company
import java.util.*

object CompanyDtoMapper {

        fun companyToEntity(req : RegisterCompanyReq,detectiveId :UUID) : Company {
            return Company(
                req.companyName,
                req.addr,
                req.phoneNumber,
                req.introduction,
                req.specialization,
                detectiveId)
        }


}