package com.api.pladder.application.service.user.boss.manager

import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.boss.mapper.DtoMapper.toEntity
import com.api.pladder.application.dto.user.boss.mapper.DtoMapper.updateInfo
import com.api.pladder.application.dto.user.boss.request.RegisterBossReq
import com.api.pladder.application.dto.user.boss.request.UpdateInfoBossReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.DetectiveRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BossManager(
    private val detectiveRepository: DetectiveRepository

) : JpaService<Detective, String> {

    override var jpaRepository: BaseRepository<Detective, String> = detectiveRepository

    fun register(req : RegisterBossReq): UserResp {
        val encoder = BCryptPasswordEncoder()
        val convertPasswd = encoder.encode(req.passwd)
        req.setConvertPasswd(convertPasswd)
        val boss = toEntity(req)
        return UserResp(save(boss))
    }
    fun update(req: UpdateInfoBossReq): UserResp {
        val boss = findById(req.email)
        updateInfo(boss,req)
        return UserResp(save(boss))
    }
    fun setCompany(companyId: String) {
        //TODO
    }


}