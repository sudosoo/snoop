package com.api.pladder.application.service.company.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.company.CompanyRepository
import java.util.*

class CompanyManager (
    val repository: CompanyRepository
): JpaService<Company, UUID> {
    override var jpaRepository: BaseRepository<Company, UUID> = repository


}