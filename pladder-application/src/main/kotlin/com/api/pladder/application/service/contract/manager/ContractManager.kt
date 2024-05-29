package com.api.pladder.application.service.contract.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.ContractRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContractManager (
    private val repository: ContractRepository

): JpaService<Contract, UUID> {

    override var jpaRepository: BaseRepository<Contract, UUID> = repository
}
