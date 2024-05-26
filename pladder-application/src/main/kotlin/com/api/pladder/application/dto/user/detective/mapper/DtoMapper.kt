package com.api.pladder.application.dto.user.detective.mapper

import com.api.pladder.application.dto.user.detective.request.RegisterBossReq
import com.api.pladder.application.dto.user.detective.request.UpdateInfoBossReq
import com.api.pladder.domain.entity.user.Detective


internal object DtoMapper {

    fun toEntity(req : RegisterBossReq) : Detective {
        return Detective(req.email, req.passwd, req.phoneNumber)
    }
    fun updateInfo(detective: Detective, req:UpdateInfoBossReq) {
        return detective.updateInfo(req.phoneNumber)
    }
}