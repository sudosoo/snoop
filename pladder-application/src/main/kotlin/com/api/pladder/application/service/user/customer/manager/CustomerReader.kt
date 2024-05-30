package com.api.pladder.application.service.user.customer.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.CustomerRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerReader(
    private val customerRepository: CustomerRepository
): JpaService<Customer,UUID> {

    override var jpaRepository: BaseRepository<Customer, UUID> = customerRepository

    fun findByEmail(email: String): Customer {
        return findByEmail(email)
    }

}