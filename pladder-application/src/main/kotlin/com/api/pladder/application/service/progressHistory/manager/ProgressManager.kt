package com.api.pladder.application.service.progressHistory.manager

import com.api.pladder.application.dto.progressHistory.mapper.ProgressHistoryDtoMapper
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.progressHistory.Progress
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.progressHistory.ProgressHistoryRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProgressManager(
    private val repository: ProgressHistoryRepository
):JpaService<Progress, UUID> {

    override var jpaRepository: BaseRepository<Progress, UUID> = repository

    fun register(content : String) : Progress {
        val progress = ProgressHistoryDtoMapper.toEntity(content)
        return save(progress)
    }

    fun saveEntity(progress: Progress) = save(progress)



}
