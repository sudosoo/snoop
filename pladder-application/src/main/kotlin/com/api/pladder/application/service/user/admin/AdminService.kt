package com.api.pladder.application.service.user.admin

import com.api.pladder.application.dto.auth.request.SignInUserReq
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.core.exception.AccessDeniedException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AdminService :UserService {
    override fun register(request: RegisterUserReq): UserResp {
        throw AccessDeniedException("관리자는 사용할 수 없는 기능 입니다.")
    }

    override fun signInFromReq(request: SignInUserReq): UserResp {
        throw AccessDeniedException("관리자는 사용할 수 없는 기능 입니다.")
    }

    override fun withdrawn(userId: UUID) {
        throw AccessDeniedException("관리자는 사용할 수 없는 기능 입니다.")
    }

    override fun updatePasswd(userId: UUID, request: UpdatePasswdUserReq): UserResp {
        throw AccessDeniedException("관리자는 사용할 수 없는 기능 입니다.")
    }

    override fun validUser(userId: UUID, passwd: String): Boolean {
        throw AccessDeniedException("관리자는 사용할 수 없는 기능 입니다.")
    }

}