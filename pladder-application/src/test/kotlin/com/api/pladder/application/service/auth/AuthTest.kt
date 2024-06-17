package com.api.pladder.application.service.auth

import com.api.pladder.application.dto.user.common.response.UserResp
import com.api.pladder.application.service.TestData.adminSignup
import com.api.pladder.application.service.TestData.customer
import com.api.pladder.application.service.TestData.customerSignup
import com.api.pladder.application.service.TestData.detective
import com.api.pladder.application.service.TestData.detectiveSignup
import com.api.pladder.application.service.user.admin.AdminService
import com.api.pladder.application.service.user.customer.CustomerService
import com.api.pladder.application.service.user.detective.DetectiveService
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.utils.jwt.JwtUtil
import com.api.pladder.core.utils.securityProvider.SecurityProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
class AuthTest : BehaviorSpec({
    val jwtUtil: JwtUtil = mockk()
    val securityProvider: SecurityProvider = mockk()
    val detectiveService: DetectiveService = mockk()
    val customerService: CustomerService = mockk()
    val adminService: AdminService = mockk()

    lateinit var service : AuthService

    beforeContainer {

        every { securityProvider.passwdBCryptConvert(any()) } returns "convertPasswd"
        every { customerService.register(any()) } returns UserResp(customer)
        every { detectiveService.register(any()) } returns UserResp(detective)
        every { adminService.register(any()) } throws AccessDeniedException("관리자는 회원가입을 할 수 없습니다.")

        service = AuthService(
            jwtUtil,
            securityProvider,
            detectiveService,
            customerService,
            adminService
        )
    }

    Given("유저 로그인 관련 테스트") {

        When("회원이 가입 요청을 하면") {
            val user = service.signUp(customerSignup)

            Then("회원타입 객체가 생성 된다") {
                user.status shouldBe(UserType.CUSTOMER.stringStatus)
            }
        }
        When("탐정이 가입 요청을 하면") {
            val user = service.signUp(detectiveSignup)

            Then("탐정타입의 객체가 생성 된다") {
                user.status shouldBe(UserType.DETECTIVE.stringStatus)
            }
        }
        When("관리자가 가입 요청을 하면") {
            val exception = shouldThrow<AccessDeniedException> {
                service.signUp(adminSignup)
            }
            Then("예외를 발생 시킨다.") {
                exception.shouldBeTypeOf<AccessDeniedException>()
            }
        }

    }

    afterContainer {
        clearMocks(jwtUtil, securityProvider, detectiveService, customerService, adminService)
    }

})
