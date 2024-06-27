package com.api.pladder.application.service.company

import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.auth.AuthService
import com.api.pladder.application.service.auth.TestData
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.application.service.user.admin.AdminService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.application.service.user.detective.DetectiveService
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.utils.jwt.JwtUtil
import com.api.pladder.core.utils.securityProvider.SecurityProvider
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("local")
class CompanyServiceTests  : BehaviorSpec({
    val manager : CompanyManager = mockk()
    val reader : CompanyReader = mockk()
    val fileService: FileService = mockk()

    lateinit var service : CompanyService

    beforeContainer {
        every { service.register(any()) } returns
        every { service. } returns UserResp(TestData.customer)

        service = CompanyService(
            manager,
            reader,
            fileService,
        )
    }


})
