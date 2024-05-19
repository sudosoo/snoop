package com.api.pladder.application.service.user.boss.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.BossRepository
import org.springframework.stereotype.Component

@Component
class BossReader(
    private val customerRepository: BossRepository
): JpaService<Boss, String> {

    override var jpaRepository: BaseRepository<Boss, String> = customerRepository
    fun findByEmail(email: String): Boss {
        return findById(email)
    }

}