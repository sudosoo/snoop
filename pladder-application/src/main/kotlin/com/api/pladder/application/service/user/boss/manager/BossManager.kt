package com.api.pladder.application.service.user.boss.manager

import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.boss.request.RegisterBossReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.BossRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class BossManager(
    private var bossRepository: BossRepository

) : JpaService<Boss, String> {

    override var jpaRepository: BaseRepository<Boss, String> = bossRepository

    fun register(req : RegisterBossReq): UserResp {
        val encoder = BCryptPasswordEncoder()
        val convertPasswd = encoder.encode(req.passwd)
        val boss = Boss(req.email, convertPasswd , req.phoneNumber)
        return UserResp(save(boss))
    }

    fun setCompany(companyId: String) {
        //TODO
    }


}