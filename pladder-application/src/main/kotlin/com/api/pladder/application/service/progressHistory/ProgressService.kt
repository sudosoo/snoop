package com.api.pladder.application.service.progressHistory

import com.api.pladder.application.dto.progress.request.ProgressRegisterReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.progressHistory.manager.ProgressManager
import com.api.pladder.application.service.progressHistory.reader.ProgressReader
import com.api.pladder.domain.entity.contract.Contract
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.contracts.contract

@Service
class ProgressService (
    private val progressManager: ProgressManager,
    private val progressReader: ProgressReader,
    private val contractService: ContractService
){


    fun registerProgress(req : ProgressRegisterReq) {
        var dateParse = LocalDate.parse(req.date)

    }

}