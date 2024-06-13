package com.api.pladder.application.service.contractContent

import com.api.pladder.application.dto.contractContent.mapper.ContractContentDtoMapper
import com.api.pladder.application.dto.contractContent.request.RegisterContractContentReq
import com.api.pladder.application.dto.contractContent.request.UpdateContractContentReq
import com.api.pladder.application.service.contractContent.manager.ContractContentManager
import com.api.pladder.application.service.contractContent.reader.ContractContentReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContractContentService (
    val manager: ContractContentManager,
    val reader: ContractContentReader
){
    fun register(req : RegisterContractContentReq){
        val contractContent  = ContractContentDtoMapper.toEntity(req)
        manager.register(contractContent)
    }

    fun update(req : UpdateContractContentReq){
        val contractContent = reader.findById(UUID.fromString(req.contractContentId))
        ContractContentDtoMapper.update(contractContent,req)
    }

}