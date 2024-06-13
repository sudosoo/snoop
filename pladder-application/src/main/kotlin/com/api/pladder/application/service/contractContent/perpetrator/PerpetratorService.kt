package com.api.pladder.application.service.contractContent.perpetrator

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.application.dto.contractContent.mapper.PerpetratorDtoMapper
import com.api.pladder.application.dto.contractContent.perpetrator.RegisterPerpetratorReq
import com.api.pladder.application.dto.contractContent.perpetrator.UpdatePerpetratorReq
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
): JpaService<Perpetrator, UUID> {
    override var jpaRepository: BaseRepository<Perpetrator, UUID> = repository

    fun register(req : RegisterPerpetratorReq){
        if(req.accompliceId != null){
            appendAccomplice(UUID.fromString(req.accompliceId), req)
            return
        }
        val perpetrator = PerpetratorDtoMapper.toEntity(req)
        repository.save(perpetrator)
    }

    fun appendAccomplice(perpetratorId: UUID, req: RegisterPerpetratorReq){
        val perpetrator = findById(perpetratorId)
        val accomplice = PerpetratorDtoMapper.toEntity(req)
        perpetrator.appendAccomplice(accomplice)
        save(accomplice)
    }

    fun update(req :UpdatePerpetratorReq){
        val perpetrator = findById(UUID.fromString(req.perpetratorId))
        PerpetratorDtoMapper.update(perpetrator,req)
    }

    fun delete(perpetratorId: UUID){
        val perpetrator = findById(perpetratorId)
        deleteById(perpetrator.perpetratorId!!)
    }

}