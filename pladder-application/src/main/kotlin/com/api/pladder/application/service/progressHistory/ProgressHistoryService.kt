package com.api.pladder.application.service.progressHistory

import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryRegisterReq
import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryUpdateReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.progressHistory.manager.ProgressManager
import com.api.pladder.application.service.progressHistory.reader.ProgressReader
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProgressHistoryService (
    private val manager: ProgressManager,
    private val reader: ProgressReader,
    private val contractService: ContractService
){

    fun registerProgress(req : ProgressHistoryRegisterReq) {
        val contract = contractService.findById(UUID.fromString(req.contractId))
        val progressHistory = manager.register(req.content)
        progressHistory.addContract(contract)
    }

    fun getProgressHistories(contractId: String,pageReq:PageRequest ): PageImpl<ProgressListResp> {
        val pageable = getBasePageableWithSorting(pageReq)
        val histories = reader.getHistoriesByContractId(UUID.fromString(contractId))
        return PageImpl(histories, pageReq, histories.size.toLong())
    }

    fun updateProgress(req : ProgressHistoryUpdateReq){
        val progress = reader.findById(UUID.fromString(req.contractId))
        progress.updateContent(req.content)
        manager.saveEntity(progress)
    }

    fun deleteProgress(progressId: String) = manager.deleteById(UUID.fromString(progressId))


}