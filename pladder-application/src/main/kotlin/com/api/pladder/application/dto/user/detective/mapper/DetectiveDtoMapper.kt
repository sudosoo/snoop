package com.api.pladder.application.dto.user.detective.mapper

import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.application.dto.user.common.request.UpdateInfoUserReq
import com.api.pladder.domain.entity.user.Detective


object DetectiveDtoMapper {

    fun toEntity(req : RegisterUserReq) : Detective {
        return Detective(req.email, req.passwd, req.phoneNumber)
    }
    fun updateInfo(detective: Detective, req: UpdateInfoUserReq) {
        return detective.updateInfo(req.phoneNumber)
    }
}