package com.api.pladder.application.service.contractContent.perpetrator

import com.api.pladder.application.dto.contractContent.mapper.PerpetratorDtoMapper
import com.api.pladder.application.dto.contractContent.perpetrator.RegisterPerpetratorReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.contract.Perpetrator
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.PerpetratorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class PerpetratorService(
    val repository : PerpetratorRepository
):JpaService<Perpetrator, UUID>{
    override var jpaRepository: BaseRepository<Perpetrator, UUID> = repository

    fun register(req : RegisterPerpetratorReq){
        val perpetrator = PerpetratorDtoMapper.toEntity(req)
        repository.save(perpetrator)
    }

    fun appendAccomplice(perpetratorId: UUID, req: RegisterPerpetratorReq){
        val perpetrator = findById(perpetratorId)
        val accomplice = PerpetratorDtoMapper.toEntity(req)
        perpetrator.appendAccomplice(accomplice)
        save(accomplice)
    }


}