package com.api.pladder.application.service.user.detective.manager

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

@Component
class DetectiveManager(
    private val detectiveRepository: DetectiveRepository

) : JpaService<Detective, String> {

    override var jpaRepository: BaseRepository<Detective, String> = detectiveRepository

    fun register(req : RegisterUserReq): Detective {
        val detective = toEntity(req)
        return save(detective)
    }
    fun update(req: UpdateInfoBossReq): Detective {
        val boss = findById(req.email)
        updateInfo(boss,req)
        return save(boss)
    }
    fun setCompany(companyId: String) {
        //TODO
    }


}