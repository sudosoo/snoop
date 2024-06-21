package com.api.pladder.application.service.user.detective

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.application.dto.user.common.request.UpdatePasswdUserReq
import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.dto.user.detective.mapper.DetectiveDtoMapper
import com.api.pladder.application.dto.user.detective.request.RegisterDetectiveCareerReq
import com.api.pladder.application.service.user.common.UserService
import com.api.pladder.application.service.user.detective.manager.DetectiveManager
import com.api.pladder.application.service.user.detective.reader.DetectiveReader
import com.api.pladder.core.obj.AuthUserObject
import org.springframework.stereotype.Service


@Service
class DetectiveService : UserService {

    private lateinit var manager: DetectiveManager
    private lateinit var reader: DetectiveReader

    override fun findByEmail(email: String): UserResp {
        return UserResp(reader.findByEmail(email))
    }

    override fun withdrawn(userId: String) {
        manager.withdrawn(userId)
    }

    override fun register(request: RegisterUserReq): UserResp {
        return UserResp(manager.register(request))
    }

    override fun updatePasswd(request: UpdatePasswdUserReq): UserResp {
        return UserResp(manager.updatePasswd(request))
    }

    fun updateInfo(requestUserId : String, request: UpdateInfoUserReq): UserResp {
        return UserResp(manager.updateInfo(requestUserId, request))
    }

    fun registerCareer(request: List<RegisterDetectiveCareerReq>,authObj:AuthUserObject ): UserResp {
        val model = reader.findById(authObj.userId!!)
        request.forEach { DetectiveDtoMapper.updateCareer(model, it) }
        manager.save(model)
        return UserResp(model)
    }

}