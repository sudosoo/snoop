package com.api.pladder.application.dto.progressHistory.mapper

import com.api.pladder.domain.entity.progressHistory.Progress

object ProgressHistoryDtoMapper {
    fun toEntity(content: String) = Progress(content)



}