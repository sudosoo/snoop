package com.api.pladder.application.service.progressHistory

import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.application.dto.progressHistory.mapper.ProgressHistoryDtoMapper
import com.api.pladder.application.dto.progressHistory.request.ProgressContentRegisterReq
import com.api.pladder.application.dto.progressHistory.request.ProgressHistoryUpdateReq
import com.api.pladder.application.dto.progressHistory.response.ProgressHistoryResp
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.image.ImageService
import com.api.pladder.application.service.progressHistory.manager.ProgressManager
import com.api.pladder.application.service.progressHistory.reader.ProgressReader
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProgressHistoryService (
    private val manager: ProgressManager,
    private val reader: ProgressReader,
    private val contractService: ContractService,
    private val imageService: ImageService,
){

    fun register(req : ProgressContentRegisterReq,imageReq: ImageReq) {

        val image = imageService.save(imageReq)
        val contract = contractService.findById(UUID.fromString(req.contractId))
        val progressHistory = manager.register(req.content)


        progressHistory.addContract(contract)
    }

    fun getHistories(contractId: String, pageReq:PageRequest ): PageImpl<ProgressHistoryResp> {
        val contract = contractService.findById(UUID.fromString(contractId))
        val histories = reader.getHistoriesByContractId(contract,pageReq)
        return PageImpl(
            histories.content.map { ProgressHistoryDtoMapper.toRespDto(it)}.toList(),
            pageReq,
            histories.size.toLong())
    }

    fun updateContent(req : ProgressHistoryUpdateReq){
        val progress = reader.findById(UUID.fromString(req.contractId))
        progress.updateContent(req.content)
        manager.saveEntity(progress)
    }

    fun delete(progressId: String) = manager.deleteById(UUID.fromString(progressId))



}