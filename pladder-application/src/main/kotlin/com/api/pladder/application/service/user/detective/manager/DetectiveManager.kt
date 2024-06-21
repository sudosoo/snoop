package com.api.pladder.application.service.user.detective.manager

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.detective.mapper.DetectiveDtoMapper
import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.DetectiveRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DetectiveManager(
    private val repository: DetectiveRepository
) : JpaService<Detective, UUID> {

    override var jpaRepository: BaseRepository<Detective, UUID> = repository

    fun register(request : RegisterUserReq): Detective {
        val detective = DetectiveDtoMapper.toEntity(request)
        return save(detective)
    }
    fun updatePasswd(request: UpdatePasswdUserReq): Detective {
        val detective = repository.findByEmailAndPasswd(request.email,request.passwd).orElseThrow{ throw NotFoundException("Detective not found") }
        detective.updatePasswd(request.reqUpdatePasswd)
        return save(detective)
    }
    fun setCompany(companyId: String) {
        //TODO
    }

    fun updateInfo(requestUserId :String, request: UpdateInfoUserReq): Detective {
        val detective = findById(UUID.fromString(requestUserId))
        DetectiveDtoMapper.updateInfo(detective, request)
        return save(detective)
    }

    fun withdrawn(reqId: String) {
        deleteById(UUID.fromString(reqId))
    }


}