package com.api.pladder.application.service.user.boss.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.DetectiveRepository
import org.springframework.stereotype.Component

@Component
class BossReader(
    private val customerRepository: DetectiveRepository
): JpaService<Detective, String> {

    override var jpaRepository: BaseRepository<Detective, String> = customerRepository
    fun findByEmail(email: String): Detective {
        return findById(email)
    }

}