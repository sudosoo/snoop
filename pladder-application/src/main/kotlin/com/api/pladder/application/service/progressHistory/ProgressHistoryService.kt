package com.api.pladder.application.service.progressHistory

import com.api.pladder.application.dto.progressHistory.mapper.ProgressHistoryDtoMapper
import com.api.pladder.application.dto.progressHistory.request.ProgressContentRegisterReq
import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryUpdateReq
import com.api.pladder.application.dto.progressHistory.response.ProgressHistoryResp
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.progressHistory.manager.ProgressManager
import com.api.pladder.application.service.progressHistory.reader.ProgressReader
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProgressHistoryService (
    private val manager: ProgressManager,
    private val reader: ProgressReader,
    private val contractService: ContractService,
){
    fun register(request : ProgressContentRegisterReq) {
        val contract = contractService.findById(UUID.fromString(request.contractId))
        val progressHistory = manager.register(request.content)
        progressHistory.addContract(contract)
    }

    fun getHistories(contractId: String, pageReq:PageRequest ,authObj: AuthUserObject): PageImpl<ProgressHistoryResp> {
        val contract = contractService.findById(UUID.fromString(contractId))
        if (contract.customerId != authObj.userId || contract.company.detectiveId != authObj.userId ){
            throw AccessDeniedException("해당 계약에 대한 접근 권한이 없습니다.")
        }
        val histories = reader.getHistoriesByContractId(contract,pageReq)
        return PageImpl(
            histories.content.map { ProgressHistoryDtoMapper.toRespDto(it)}.toList(),
            pageReq,
            histories.size.toLong())
    }

    fun updateContent(request : ProgressHistoryUpdateReq){
        val progress = reader.findById(UUID.fromString(request.contractId))
        progress.updateContent(request.content)
        manager.saveEntity(progress)
    }

    fun delete(progressId: String) = manager.deleteById(UUID.fromString(progressId))


}