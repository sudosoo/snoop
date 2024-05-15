package com.api.pladder.application.service.user.customer.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerReader(
    private val customerRepository: CustomerRepository
): JpaService<Customer,String> {

    override var jpaRepository: BaseRepository<Customer, String> = customerRepository

    fun findByEmail(email: String): Customer {
        return findById(email)
    }

}