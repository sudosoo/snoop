package com.api.pladder.application.service.contractContent.victim

import com.api.pladder.application.dto.contractContent.mapper.VictimDtoMapper
import com.api.pladder.application.dto.contractContent.victim.RegisterVictimReq
import com.api.pladder.application.dto.contractContent.victim.RespVictim
import com.api.pladder.application.dto.contractContent.victim.UpdateVictimReq
import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.domain.entity.contract.Victim
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.VictimRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VictimService(
    val repository : VictimRepository
) : JpaService<Victim, UUID> {
    override var jpaRepository: BaseRepository<Victim, UUID> = repository

    fun register(req : RegisterVictimReq){
        val victim = VictimDtoMapper.toEntity(req)
        repository.save(victim)
    }

    fun update(victimId: String, req : UpdateVictimReq){
        val victim = repository.findById(UUID.fromString(victimId))
                            .orElseThrow { Exception("피해자 정보가 없습니다.") }
        VictimDtoMapper.update(victim,req)
        save(victim)
    }

    fun findById(contractId : String) : RespVictim {
        val victim = repository.findByContractId(UUID.fromString(contractId))
                                .orElseThrow{NotFoundException("피해자 정보가 없습니다.")}
        return RespVictim(victim)
    }

}