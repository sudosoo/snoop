package com.api.pladder.application.service.user.admin

import com.api.pladder.application.dto.user.UserResp
import com.api.pladder.application.service.user.admin.manager.AdminReader
import com.api.pladder.domain.entity.user.Admin
import org.springframework.stereotype.Service

@Service
class AdminService{
    private lateinit var adminReader: AdminReader

    fun findById(adminId: String): Admin {
        return adminReader.findById(adminId)
    }
}