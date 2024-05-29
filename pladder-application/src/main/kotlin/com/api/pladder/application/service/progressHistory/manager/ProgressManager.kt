package com.api.pladder.application.service.progressHistory.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.progressHistory.ProgressHistory
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.progressHistory.ProgressHistoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProgressManager(
    private val repository: ProgressHistoryRepository
):JpaService<ProgressHistory, UUID> {

    override var jpaRepository: BaseRepository<ProgressHistory, UUID> = repository

}
