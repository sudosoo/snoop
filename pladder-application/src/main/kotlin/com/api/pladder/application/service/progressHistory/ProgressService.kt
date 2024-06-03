package com.api.pladder.application.service.progressHistory

import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryRegisterReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.progressHistory.manager.ProgressManager
import com.api.pladder.application.service.progressHistory.reader.ProgressReader
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ProgressService (
    private val progressManager: ProgressManager,
    private val progressReader: ProgressReader,
    private val contractService: ContractService
){


    fun registerProgress(req : ProgressHistoryRegisterReq) {
        var dateParse = LocalDate.parse(req.date)

    }

}