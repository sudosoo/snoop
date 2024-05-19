package com.api.pladder.application.service.user.admin.manager

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.user.Admin
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.user.AdminRepository
import org.springframework.stereotype.Component

@Component
class AdminReader (
    private var adminRepository: AdminRepository
):JpaService<Admin,String>{

    //TODO property에 admin 계정 만들어서 넣기
    private lateinit var adminEmail: String
    private lateinit var adminId: String

    override var jpaRepository: BaseRepository<Admin, String> = adminRepository

    fun findByEmail(adminId: String): Admin {
        return findById(adminId)
    }

}