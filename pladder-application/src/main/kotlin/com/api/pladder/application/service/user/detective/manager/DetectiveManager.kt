package com.api.pladder.application.service.user.detective.manager

import com.api.pladder.application.core.exception.NotFoundException
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.detective.mapper.DtoMapper.toEntity
import com.api.pladder.application.dto.user.detective.mapper.DtoMapper.updateInfo
import com.api.pladder.application.dto.user.detective.request.UpdateInfoBossReq
import com.api.pladder.application.service.common.jpa.JpaService
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
        val detective = toEntity(req)
        return save(detective)
    }
    fun update(req: UpdateInfoBossReq): Detective {
        val detective = repository.findByEmail(req.email).orElseThrow{ throw NotFoundException("Detective not found")}
        updateInfo(detective,req)
        return save(detective)
    }
    fun setCompany(companyId: String) {
        //TODO
    }


}