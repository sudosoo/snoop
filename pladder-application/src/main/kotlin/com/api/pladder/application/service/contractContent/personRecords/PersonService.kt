package com.api.pladder.application.service.contractContent.personRecords

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.application.dto.contractContent.mapper.PersonDtoMapper
import com.api.pladder.application.dto.contractContent.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contractContent.person.request.UpdatePersonReq
import com.api.pladder.application.dto.contractContent.person.response.PersonResp
import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.domain.entity.contract.PersonRecord
import com.api.pladder.domain.entity.contract.enums.PersonStatus
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.PersonRecordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class PersonService(
    val repository : PersonRecordRepository
): JpaService<PersonRecord, UUID> {
    override var jpaRepository: BaseRepository<PersonRecord, UUID> = repository

    fun register(req : RegisterPersonReq) : PersonResp {
        val person = PersonDtoMapper.toEntity(req)
        return PersonResp(repository.save(person))
    }

    fun appendAccomplice(personId: UUID, req: RegisterPersonReq): PersonResp{
        val perpetrator = repository.findByIdAndStatus(personId,PersonStatus.PERPETRATOR)
            .orElseThrow{ NotFoundException("존재하지 않는 가해자ID 입니다 : ${personId}")
            }
        val accomplice = PersonDtoMapper.toEntity(req)
        perpetrator.appendAccomplice(accomplice)
        return PersonResp(save(accomplice))
    }

    fun update(req : UpdatePersonReq) : PersonResp {
        val model = findById(UUID.fromString(req.personId))
        PersonDtoMapper.update(model,req)
        return PersonResp(save(model))
    }

    fun delete(personId: UUID){
        findById(personId)
        deleteById(personId)
    }

}