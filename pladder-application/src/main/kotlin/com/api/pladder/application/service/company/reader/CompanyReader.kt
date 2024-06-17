package com.api.pladder.application.service.company.reader

import com.api.pladder.application.common.jpa.JpaService
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
    fun getInstanceByDetectiveId(detectiveId: UUID) : Company {
        return repository.findByDetectiveId(detectiveId)
            .orElseThrow{throw IllegalArgumentException("등록된 회사가 존재하지 않습니다.")}
    }


}