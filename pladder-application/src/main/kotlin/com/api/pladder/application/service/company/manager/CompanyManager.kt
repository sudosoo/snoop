package com.api.pladder.application.service.company.manager

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.application.dto.company.mapper.CompanyDtoMapper
import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.company.CompanyRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CompanyManager (
    private val repository: CompanyRepository
): JpaService<Company, UUID> {
    override var jpaRepository: BaseRepository<Company, UUID> = repository

    fun register(request: RegisterCompanyReq, detectiveId: UUID): Company {
        val company = CompanyDtoMapper.toEntity(request,detectiveId)
        return save(company)
    }

    fun updateInfo(company: Company, request: UpdateCompanyInfoReq): Company {
        val company = CompanyDtoMapper.updateInfo(company,request)
        return save(company)
    }




}