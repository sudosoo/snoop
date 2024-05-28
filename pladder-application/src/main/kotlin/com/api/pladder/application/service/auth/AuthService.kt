package com.api.pladder.application.service.auth

import com.api.pladder.domain.entity.user.enums.UserType
import com.api.pladder.application.auth.jwt.JwtUtil
import com.api.pladder.application.core.enums.HeaderPrefix.AUTHORIZATION
import com.api.pladder.application.core.exception.InvalidRequestException
import com.api.pladder.application.dto.auth.request.AuthReq
import com.api.pladder.application.dto.auth.request.SignInUserReq
import com.api.pladder.application.dto.user.common.response.WithdrawResp
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.user.admin.AdminService
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.application.service.user.detective.DetectiveService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtUtil: JwtUtil,
    private val detectiveService: DetectiveService,
    private val customerService: CustomerService,
    private val adminService: AdminService,
) {

    fun signUp(req: RegisterUserReq) : UserResp{
        val userService = getUserService(req.userType)
        val userRes = userService.register(req)
        return userRes
    }

    fun signIn(req: SignInUserReq, authReq: AuthReq, servletResp: HttpServletResponse) : UserResp {
        val userService = getUserService(req.userType)
             // login
        val userResp = userService.findByEmail(req.email!!)
        if (userResp.isActive != true)
            throw InvalidRequestException("로그인 할수 없는 상태입니다. 관리자에게 문의하세요. (현재 상태:${userResp.isActive})")

            //TODO Spring security 기능 추가 필요
    //        val authorities = mutableListOf<GrantedAuthority>()
    //        when(req.userType){
    //            ADMIN -> authorities.add(SimpleGrantedAuthority("ADMIN"))
    //            BOSS -> authorities.add(SimpleGrantedAuthority("BOSS"))
    //            CUSTOMER -> authorities.add(SimpleGrantedAuthority("CUSTOMER"))
    //            UNKNOWN -> authorities.add(SimpleGrantedAuthority("OPEN")) }
            val accessToken: String = jwtUtil.generate(userResp.userId, req.userType)
        //TODO 토큰 어디에 넣을건지 ?
        //servletResp.addCookie(cookie)
        servletResp.addHeader(AUTHORIZATION, accessToken)
        return userResp
    }

    fun signOut(){
        // TODO : check token
        // TODO : delete token
    }


    fun withdraw(authReq: AuthReq): WithdrawResp {
        // TODO : unlink auth service
        val userService = getUserService(authReq.userType)
        return userService.withdraw(
            (authReq.userId ?:throw InvalidRequestException("사용자 아이디는 null 일 수 없습니다.")
                    ).toString()
        )
    }

    private fun getUserService(userType: UserType): UserService {
        return when (userType){
            UserType.ADMIN -> return adminService
            UserType.DETECTIVE -> return detectiveService
            UserType.CUSTOMER -> return customerService
            else -> {throw InvalidRequestException("지원하지 않는 사용자 유형입니다.")}
        }
    }
}