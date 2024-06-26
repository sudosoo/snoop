package com.api.pladder.application.service.user.customer.reader

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.CustomerRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerReader(
    private val repository: CustomerRepository
) : JpaService<Customer, UUID> {

    override var jpaRepository: BaseRepository<Customer, UUID> = repository

    fun findByNicknameAndPasswd(nickname: String, passwd: String): Customer {
        return repository.findByNicknameAndPasswd(nickname, passwd)
            .orElseThrow { throw NoSuchElementException("해당 닉네임을 가진 유저가 없습니다.") }
    }


}