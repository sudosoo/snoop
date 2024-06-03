package com.api.pladder.application.service.contractContent.manager

import com.api.pladder.application.dto.contractContent.mapper.ContractContentDtoMapper
import com.api.pladder.application.dto.contractContent.request.RegisterContractContentReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.contract.ContractContent
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.ContractContentRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ContractContentManager (
    val repository: ContractContentRepository
) : JpaService<ContractContent, UUID> {
    override var jpaRepository: BaseRepository<ContractContent, UUID> = repository
    fun register(req : RegisterContractContentReq){
        val contractContent = ContractContentDtoMapper.toEntity(req.contractId, req.content)
        repository.save(contractContent)
    }
}
