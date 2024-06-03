package com.api.pladder.application.service.auth

import com.api.pladder.application.dto.auth.request.SignInUserReq
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.request.WithdrawnUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.user.admin.AdminService
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.application.service.user.detective.DetectiveService
import com.api.pladder.core.enums.HeaderPrefix.AUTHORIZATION
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.InvalidRequestException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.jwt.JwtUtil
import com.api.pladder.core.utils.provider.SecurityProvider
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtUtil: JwtUtil,
    private val securityProvider: SecurityProvider,
    private val detectiveService: DetectiveService,
    private val customerService: CustomerService,
    private val adminService: AdminService,
) {

    fun signUp(req: RegisterUserReq, authObj: AuthUserObject): UserResp{

        val convertPasswd= securityProvider.passwdBCryptConvert(req.passwd!!)
        req.updateConvertPasswd(convertPasswd)

        val userService = getUserService(authObj.userType)
        val userRes = userService.register(req)
        return userRes
    }

    fun signIn(req: SignInUserReq, servletResp: HttpServletResponse) : UserResp {
        val convertPasswd= securityProvider.passwdBCryptConvert(req.passwd!!)
        req.updateConvertPasswd(convertPasswd)

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
        //TODO 토큰 어디에 넣을건지 쿠키?
        //servletResp.addCookie(cookie)
        servletResp.addHeader(AUTHORIZATION, accessToken)
        return userResp
    }

    fun updatePasswd(req: UpdatePasswdUserReq, authObj:AuthUserObject) : UserResp {
        val convertReqPasswd = securityProvider.passwdBCryptConvert(req.passwd!!)


        val userService = getUserService(authObj.userType)
        val userRes = userService.updatePasswd(req)
        return userRes
    }



    fun signOut(){
        // TODO : check token
        // TODO : delete token
    }


    fun withdrawn(req : WithdrawnUserReq,authObj: AuthUserObject) {
        val userService = getUserService(authObj.userType)
        userService.withdrawn(
            (authObj.userId ?:throw InvalidRequestException("사용자 아이디는 null 일 수 없습니다.")
                    ).toString()
        )
    }

    private fun getUserService(userType: UserType): UserService {
        return when (userType){
            UserType.ADMIN -> adminService
            UserType.DETECTIVE -> detectiveService
            UserType.CUSTOMER -> customerService
            else -> {
                throw InvalidRequestException("지원하지 않는 사용자 유형입니다.")
            }
        }

    }
}