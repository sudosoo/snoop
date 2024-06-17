package com.api.pladder.application.service.contract.reader

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.contract.enums.ContractStatus
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.ContractRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContractReader (
    private val repository: ContractRepository
): JpaService<Contract, UUID> {

    override var jpaRepository: BaseRepository<Contract, UUID> = repository
    fun findAllById(id : UUID): List<Contract> {
        return repository.findAll()
    }
    fun findWaitingContractByCompany(company: Company): List<Contract> {
        return repository.findAllByCompanyAndStatus(company,ContractStatus.WAITING)
    }
}