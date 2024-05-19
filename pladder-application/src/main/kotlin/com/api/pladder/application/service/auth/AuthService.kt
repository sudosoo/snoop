package com.api.pladder.application.service.auth

import com.api.pladder.application.auth.enums.UserType
import com.api.pladder.application.auth.jwt.JwtUtil
import com.api.pladder.application.core.enums.HeaderPrefix.AUTHORIZATION
import com.api.pladder.application.core.exception.InvalidRequestException
import com.api.pladder.application.core.exception.NotFoundException
import com.api.pladder.application.dto.auth.request.AuthReq
import com.api.pladder.application.dto.auth.request.SignInReq
import com.api.pladder.application.dto.user.WithdrawResp
import com.api.pladder.application.service.user.UserService
import com.api.pladder.application.service.user.admin.AdminService
import com.api.pladder.application.service.user.boss.BossService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.domain.entity.user.User
import com.api.pladder.domain.entity.user.enums.UserStatus
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtUtil: JwtUtil,
    private val bossService: BossService,
    private val customerService: CustomerService,
    private val adminService: AdminService,
) {

    fun signIn(req: SignInReq , servletResp: HttpServletResponse) {

        val userService = getUserService(req.userType)
        val user:User
        try { // login
            user = userService.findByEmail(req.email)
            if (user.status != UserStatus.ACTIVE)
                throw InvalidRequestException("로그인 할수 없는 상태입니다. 관리자에게 문의하세요. (현재 상태:${user.status})")

        } catch (e: NotFoundException){
            throw NotFoundException("사용자 정보가 존재하지 않습니다.")
        }
        //TODO Spring security 기능 추가 필요
//        val authorities = mutableListOf<GrantedAuthority>()
//        when(req.userType){
//            ADMIN -> authorities.add(SimpleGrantedAuthority("ADMIN"))
//            BOSS -> authorities.add(SimpleGrantedAuthority("BOSS"))
//            CUSTOMER -> authorities.add(SimpleGrantedAuthority("CUSTOMER"))
//            UNKNOWN -> authorities.add(SimpleGrantedAuthority("OPEN")) }
        val accessToken: String = jwtUtil.generate(user.id, req.userType)
        //TODO 토큰 어디에 넣을건지 ?
        //servletResp.addCookie(cookie)
        servletResp.addHeader(AUTHORIZATION, accessToken)
    }

    fun signOut(){
        // TODO : check token
        // TODO : delete token
    }


    fun withdraw(authReq: AuthReq): WithdrawResp {

        // TODO : unlink auth service
        // change user status : DORMANT 휴면 계정
        val userService = getUserService(authReq.userType)
        return userService.withdraw(authReq.userId
            ?:throw InvalidRequestException("사용자 아이디는 null 일 수 없습니다."))
    }

    private fun getUserService(userType: UserType): UserService {
        return when (userType){
            UserType.BOSS -> return bossService
            UserType.CUSTOMER -> return customerService
            UserType.ADMIN -> return adminService
            else -> {throw InvalidRequestException("지원하지 않는 사용자 유형입니다.")}
        }
    }
}