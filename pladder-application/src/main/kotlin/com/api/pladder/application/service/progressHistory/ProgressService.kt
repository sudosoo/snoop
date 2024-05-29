package com.api.pladder.application.service.progressHistory

import com.api.pladder.application.dto.progress.request.ProgressRegisterReq
import com.api.pladder.application.service.progressHistory.manager.ProgressManager
import com.api.pladder.application.service.progressHistory.reader.ProgressReader
import com.api.pladder.domain.entity.contract.Contract
import org.springframework.stereotype.Service

@Service
class ProgressService (
    private val progressManager: ProgressManager,
    private val progressReader: ProgressReader,
) {

    fun registerProgress(req : ProgressRegisterReq) {

    }

}