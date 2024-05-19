package com.api.pladder.application.dto.user.boss.mapper

import com.api.pladder.application.dto.user.boss.request.RegisterBossReq
import com.api.pladder.application.dto.user.boss.request.UpdateInfoBossReq
import com.api.pladder.domain.entity.user.Boss


internal object DtoMapper {

    fun toEntity(req : RegisterBossReq) : Boss {
        return Boss(req.email, req.passwd , req.phoneNumber)
    }
    fun updateInfo(boss: Boss, req:UpdateInfoBossReq) {
        return boss.updateInfo(req.phoneNumber)
    }
}