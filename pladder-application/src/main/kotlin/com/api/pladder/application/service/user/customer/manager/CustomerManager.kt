package com.api.pladder.application.service.user.customer.manager

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.customer.mapper.DtoMapper.toEntity
import com.api.pladder.application.dto.user.customer.mapper.DtoMapper.updateInfo
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdatePasswdCustomerReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.CustomerRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerManager(
    private val customerRepository: CustomerRepository,
):JpaService<Customer, UUID> {
    override var jpaRepository: BaseRepository<Customer, UUID> = customerRepository

    fun register(req : RegisterUserReq) :Customer {
        val customer = toEntity(req)
        return save(customer)
    }

    fun update(req: UpdateInfoCustomerReq): Customer {
        val customer = findById(UUID.fromString(req.userId))
        updateInfo(customer,req)
        return save(customer)
    }

    fun updatePasswd(req: UpdatePasswdCustomerReq): Customer {
        val customer = customerRepository.findByEmail(req.email).orElseThrow({throw Exception("존재하지 않는 이메일입니다")})
        customer.updatePasswd(req.reqUpdatePasswd)
        return save(customer)
    }

    //임시 비밀번호 생성기
    private fun getTempPasswd(): String {
        val charSet = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
        )
        val str = StringBuilder()
        for (i in 0 until 10) {
            val idx = (charSet.size * Math.random()).toInt()
            str.append(charSet[idx])
        }
        return str.toString()
    }

}