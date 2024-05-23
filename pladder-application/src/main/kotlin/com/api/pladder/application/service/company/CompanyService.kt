package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.company.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService (
    val repository : CompanyRepository

) :JpaService<Company,String> {
    override var jpaRepository: BaseRepository<Company, String> = repository

    fun register(req: RegisterCompanyReq) {
     //TODO
     }

}