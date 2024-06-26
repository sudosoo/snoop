package com.api.pladder.application.service.contract.person

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.application.dto.contract.mapper.PersonDtoMapper
import com.api.pladder.application.dto.contract.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contract.person.request.UpdatePersonReq
import com.api.pladder.application.dto.contract.person.response.PersonResp
import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.domain.entity.contract.Person
import com.api.pladder.domain.entity.contract.enums.PersonStatus
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.contract.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class PersonService(
    val repository : PersonRepository
): JpaService<Person, UUID> {
    override var jpaRepository: BaseRepository<Person, UUID> = repository

    fun register(request : RegisterPersonReq) : PersonResp {
        val person = PersonDtoMapper.toEntity(request)
        return PersonResp(repository.save(person))
    }

    fun appendAccomplice(personId: UUID, request: RegisterPersonReq): PersonResp {
        val perpetrator = repository.findByIdAndStatus(personId,PersonStatus.PERPETRATOR)
            .orElseThrow{ NotFoundException("존재하지 않는 가해자 ID 입니다 : ${personId}")
            }
        val accomplice = PersonDtoMapper.toEntity(request)
        perpetrator.appendAccomplice(accomplice)
        return PersonResp(save(accomplice))
    }

    fun update(request : UpdatePersonReq) : PersonResp {
        val model = findById(UUID.fromString(request.personId))
        PersonDtoMapper.updateInfo(model,request)
        return PersonResp(save(model))
    }

    fun delete(personId: String) {
        deleteById(UUID.fromString(personId))
    }

}