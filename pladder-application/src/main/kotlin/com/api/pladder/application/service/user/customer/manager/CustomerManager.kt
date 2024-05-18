package com.api.pladder.application.service.user.customer.manager

import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.dto.user.customer.mapper.DtoMapper.toEntity
import com.api.pladder.application.dto.user.customer.mapper.DtoMapper.updateInfo
import com.api.pladder.application.dto.user.customer.request.RegisterCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdateInfoCustomerReq
import com.api.pladder.application.dto.user.customer.request.UpdatePasswdCustomerReq
import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomerManager(
    private val customerRepository: CustomerRepository,
):JpaService<Customer, String> {
    override var jpaRepository: BaseRepository<Customer, String> = customerRepository

    fun register(req : RegisterCustomerReq) {
        val encoder = BCryptPasswordEncoder()
        val convertPasswd = encoder.encode(req.passwd)
        req.setConvertPasswd(convertPasswd)
        val customer = toEntity(req)
        save(customer)
    }

    fun update(req: UpdateInfoCustomerReq):UserResp{
        val customer = findById(req.userId)
        updateInfo(customer,req)
        return UserResp(save(customer))
    }

    fun updatePasswd(req: UpdatePasswdCustomerReq):UserResp{
        val customer = customerRepository.findByEmail(req.email).orElseThrow({throw Exception("존재하지 않는 이메일입니다")})
        val encoder = BCryptPasswordEncoder()
        val convertPasswd = encoder.encode(req.passwd)
        customer.updatePasswd(convertPasswd)
        return UserResp(save(customer))
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