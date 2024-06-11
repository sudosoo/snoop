package com.api.pladder.application.service.user.detective.manager

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.detective.mapper.DetectiveDtoMapper
import com.api.pladder.application.service.common.jpa.JpaService
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

    fun register(req : RegisterUserReq): Detective {
        val detective = DetectiveDtoMapper.toEntity(req)
        return save(detective)
    }
    fun updatePasswd(req: UpdatePasswdUserReq): Detective {
        val detective = repository.findByEmailAndPasswd(req.email,req.passwd).orElseThrow{ throw NotFoundException("Detective not found") }
        detective.updatePasswd(req.reqUpdatePasswd)
        return save(detective)
    }
    fun setCompany(companyId: String) {
        //TODO
    }

    fun updateInfo(requestUserId :String, req: UpdateInfoUserReq): Detective {
        val detective = findById(UUID.fromString(requestUserId))
        DetectiveDtoMapper.updateInfo(detective, req)
        return save(detective)
    }

    fun withdrawn(reqId: String) {
        deleteById(UUID.fromString(reqId))
    }


}