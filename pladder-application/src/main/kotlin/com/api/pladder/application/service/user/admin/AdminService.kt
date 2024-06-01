package com.api.pladder.application.service.user.admin

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.common.response.WithdrawResp
import com.api.pladder.application.service.user.admin.manager.AdminReader
import com.api.pladder.application.service.user.common.UserService
import org.springframework.stereotype.Service
import com.api.pladder.core.exception.*

@Service
class AdminService :UserService {
    private lateinit var reader: AdminReader
    override fun register(req: RegisterUserReq): UserResp {
        throw AccessDeniedException("관리자는 회원가입을 할 수 없습니다.")
    }

    override fun findByEmail(email: String): UserResp {
        throw AccessDeniedException("관리자는 회원탈퇴를 할 수 없습니다.")
    }

    override fun withdraw(userId: String): WithdrawResp {
        throw AccessDeniedException("관리자는 회원탈퇴를 할 수 없습니다.")
    }

}