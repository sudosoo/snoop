package com.api.pladder.application.service.user.detective.reader

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.DetectiveRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DetectiveReader(
    private val repository: DetectiveRepository
): JpaService<Detective, UUID> {

    override var jpaRepository: BaseRepository<Detective, UUID> = repository
    fun findByEmail(email: String): Detective {
        return findByEmail(email)
    }
    fun isUser(email: String, passwd: String): Boolean {
        return !repository.findByEmailAndPasswd(email, passwd).isEmpty
    }

}