package com.api.pladder.application.service.contractContent.victim

import com.api.pladder.application.dto.contractContent.mapper.VictimDtoMapper
import com.api.pladder.application.dto.contractContent.victim.RegisterVictimReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.contract.Victim
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.VictimRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VictimService(
    val repository : VictimRepository
) : JpaService<Victim, UUID>{
    override var jpaRepository: BaseRepository<Victim, UUID> = repository

    fun register(req : RegisterVictimReq){
        val victim = VictimDtoMapper.toEntity(req)
        repository.save(victim)
    }
}