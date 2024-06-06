package com.api.pladder.application.service.progressHistory.reader

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.application.service.common.jpa.JpaSpecificationService
import com.api.pladder.domain.entity.contract.Contract
import com.api.pladder.domain.entity.progressHistory.Progress
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.progressHistory.ProgressHistoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProgressReader(
    private val repository: ProgressHistoryRepository
) : JpaService<Progress, UUID> , JpaSpecificationService<Progress,UUID> {

    override val jpaSpecRepository: BaseRepository<Progress, UUID> = repository
    override var jpaRepository: BaseRepository<Progress, UUID> = repository

    fun getHistoriesByContractId(contract: Contract ,pageable: Pageable): Page<Progress> {
        return repository.findByContract(contract,pageable)
    }



}