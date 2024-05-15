package com.api.pladder.application.service.user.boss

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.application.service.user.customer.manager.CustomerManager
import com.api.pladder.application.service.user.customer.manager.CustomerReader
import com.api.pladder.domain.entity.user.Boss
import com.api.pladder.domain.repository.common.BaseRepository
import org.springframework.stereotype.Service

@Service
class BossService{

    private lateinit var customerManager: CustomerManager
    private lateinit var customerReader: CustomerReader


}