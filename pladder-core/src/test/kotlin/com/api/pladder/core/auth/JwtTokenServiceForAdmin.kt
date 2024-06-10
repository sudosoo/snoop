package com.api.pladder.core.auth

import com.api.pladder.TestData.testAdmin
import com.api.pladder.core.utils.jwt.JwtUtil
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local")
class JwtTokenServiceForAdmin : BehaviorSpec({
    val service = JwtUtil()

    Given("Admin token 테스트") {
        When("토큰 생성 요청을 하면") {
            val token = service.generate(testAdmin)
            Then("토큰이 생성 된다") {
                print(token)
            }
        }
        When("유효한 토큰을 넣으면") {
            val token = service.generate(testAdmin)
            val isValid = service.validate(token)
            Then("토큰이 검증 된다") {
                // 토큰 검증 로직 추가
                isValid shouldBe true
            }
        }
        When("조작된 토큰을 넣으면") {
            val invalidToken = "invalidToken"
            Then("예외를 반환 한다") {
                shouldThrow<Exception> {
                    service.validate(invalidToken)
                }
            }
        }
        When("유효한 토큰을 넣으면") {
            val token = service.generate(testAdmin)
            val admin = service.convertToRequest(token)
            Then("사용자 정보가 추출된다") {
                admin.userId shouldBe(testAdmin.userId)

            }
        }
        When("조작된 토큰을 넣으면") {
            val invalidToken = "invalidToken"
            Then("예외를 반환한다") {
                shouldThrow<Exception> {
                    service.convertToRequest(invalidToken)
                }
            }
        }
    }
})