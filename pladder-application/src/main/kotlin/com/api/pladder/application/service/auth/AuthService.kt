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
import com.api.pladder.core.utils.securityProvider.SecurityProvider
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

    fun signUp(request: RegisterUserReq): UserResp{
        val convertPasswd= securityProvider.passwdBCryptConvert(request.passwd!!)
        request.updateConvertPasswd(convertPasswd)
        val userService = getUserService(request.userType)
        return userService.register(request)
    }

    fun signIn(request: SignInUserReq,servletResp: HttpServletResponse) : UserResp {
        val convertPasswd= securityProvider.passwdBCryptConvert(request.passwd!!)
        request.updateConvertPasswd(convertPasswd)

        val userService = getUserService(request.userType)
             // login
        val userResp = userService.findByEmail(request.email!!)

            //TODO Spring security 기능 추가 필요
    //        val authorities = mutableListOf<GrantedAuthority>()
    //        when(request.userType){
    //            ADMIN -> authorities.add(SimpleGrantedAuthority("ADMIN"))
    //            BOSS -> authorities.add(SimpleGrantedAuthority("BOSS"))
    //            CUSTOMER -> authorities.add(SimpleGrantedAuthority("CUSTOMER"))
    //            UNKNOWN -> authorities.add(SimpleGrantedAuthority("OPEN")) }
        val authObj = AuthUserObject(userResp.userId, request.userType)
        val accessToken: String = jwtUtil.generate(authObj)
        //TODO 토큰 어디에 넣을건지 쿠키?
        //servletResp.addCookie(cookie)
        servletResp.addHeader(AUTHORIZATION, accessToken)
        return userResp
    }

    fun updatePasswd(request: UpdatePasswdUserReq, authObj:AuthUserObject) : UserResp {
        val convertReqPasswd = securityProvider.passwdBCryptConvert(request.passwd)
        request.updateConvertPasswd(convertReqPasswd)

        val userService = getUserService(authObj.userType)
        return userService.updatePasswd(request)
    }

    fun signOut(){
        // TODO : check token
        // TODO : delete token
    }

    fun withdrawn(request : WithdrawnUserReq,authObj: AuthUserObject) {
        val userService = getUserService(authObj.userType)
        userService.withdrawn((authObj.userId).toString())
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