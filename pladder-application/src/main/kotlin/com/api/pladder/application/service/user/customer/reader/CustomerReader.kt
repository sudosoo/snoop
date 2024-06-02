package com.api.pladder.application.service.user.customer.reader

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.CustomerRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerReader(
    private val repository: CustomerRepository
): JpaService<Customer,UUID> {

    override var jpaRepository: BaseRepository<Customer, UUID> = repository

    fun findByEmail(email: String): Customer {
        return findByEmail(email)
    }

    fun isUser(email: String, passwd: String): Boolean {
        return !repository.findByEmailAndPasswd(email, passwd).isEmpty
    }

}