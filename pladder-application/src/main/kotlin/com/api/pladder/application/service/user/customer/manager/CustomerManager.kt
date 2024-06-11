package com.api.pladder.application.service.user.customer.manager

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.customer.mapper.CustomerDtoMapper
import com.api.pladder.application.dto.user.customer.mapper.CustomerDtoMapper.updateInfo
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
        val customer = CustomerDtoMapper.toEntity(req)
        return save(customer)
    }

    fun updateInfo(requestUserId :String, req: UpdateInfoUserReq): Customer {
        val customer = findById(UUID.fromString(requestUserId))
        updateInfo(customer,req)
        return save(customer)
    }

    fun updatePasswd(req: UpdatePasswdUserReq): Customer {
        val customer = customerRepository.findByNickNameAndPasswd(req.nickName,req.passwd)
            .orElseThrow({throw Exception("존재하지 않는 회원 입니다")})
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

    fun withdrawn(reqId: String) {
        deleteById(UUID.fromString(reqId))
    }

}