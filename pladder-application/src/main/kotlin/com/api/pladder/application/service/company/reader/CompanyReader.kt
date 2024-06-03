package com.api.pladder.application.service.company.reader

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.company.CompanyRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CompanyReader (
    val repository: CompanyRepository
): JpaService<Company, UUID> {
    override var jpaRepository: BaseRepository<Company, UUID> = repository

    fun getInstance(companyId: String) : Company {
        return findById(UUID.fromString(companyId))
    }
}