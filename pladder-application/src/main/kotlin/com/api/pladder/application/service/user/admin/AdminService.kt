package com.api.pladder.application.service.user.admin

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.user.admin.reader.AdminReader
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.core.exception.AccessDeniedException
import org.springframework.stereotype.Service

@Service
class AdminService :UserService {
    private lateinit var reader: AdminReader
    override fun register(req: RegisterUserReq): UserResp {
        throw AccessDeniedException("관리자는 회원가입을 할 수 없습니다.")
    }

    override fun findByEmail(email: String): UserResp {
        throw AccessDeniedException("관리자는 회원탈퇴를 할 수 없습니다.")
    }

    override fun withdrawn(userId: String) {
        throw AccessDeniedException("관리자는 회원탈퇴를 할 수 없습니다.")
    }

    override fun updatePasswd(req: UpdatePasswdUserReq): UserResp {
        throw AccessDeniedException("관리자는 비밀번호를 변경 할 수 없습니다.")
    }

}