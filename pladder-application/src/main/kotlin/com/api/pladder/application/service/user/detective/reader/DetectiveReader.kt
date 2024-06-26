package com.api.pladder.application.service.user.detective.reader

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.DetectiveRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DetectiveReader(
    private val repository: DetectiveRepository
) : JpaService<Detective, UUID> {

    override var jpaRepository: BaseRepository<Detective, UUID> = repository
    fun findByEmailAndPasswd(email: String, passwd: String): Detective {
        return repository.findByEmailAndPasswd(email, passwd).orElseThrow{
            throw NotFoundException("로그인에 실패 했습니다. 아이디 비밀번호를 확인 해주세요.")
        }
    }

}