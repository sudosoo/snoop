package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.response.CompanyResp
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.user.enums.Specialty
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import java.util.*


@SpringBootTest
@ActiveProfiles("local")
class CompanyServiceTests  : BehaviorSpec({
    val manager : CompanyManager = mockk()
    val reader : CompanyReader = mockk()
    val fileService: FileService = mockk()

    lateinit var service : CompanyService

    Given("회사 등록 관련 TEST"){
        val request = RegisterCompanyReq(
            name = "가 사무소",
            addr = "서울시 강남구",
            phoneNumber = "010-1234-5678",
            introduction = "사기전문 탐정 홍길동 사무소 입니다.",
            specialization = listOf(Specialty.FRAUD, Specialty.VIOLENCE, Specialty.SEXUAL_HARASSMENT)
        )

        val request2 = RegisterCompanyReq(
            name = "나 사무소",
            addr = "서울시 강동구",
            phoneNumber = "010-1234-5678",
            introduction = "불륜 전문 탐정 홍길동 사무소 입니다.",
            specialization = listOf(Specialty.FRAUD, Specialty.VIOLENCE, Specialty.SEXUAL_HARASSMENT)
        )

        val request3 = RegisterCompanyReq(
            name = "다 사무소",
            addr = "서울시 강북구",
            phoneNumber = "010-1234-5678",
            introduction = "종합 탐정 홍길동 사무소 입니다.",
            specialization = listOf(Specialty.FRAUD, Specialty.VIOLENCE, Specialty.SEXUAL_HARASSMENT)
        )

        val company = Company().testOf(
            UUID.randomUUID(),
            request.name,
            request.addr,
            request.phoneNumber,
            request.introduction,
            request.specialization
        )


        val pageReq = PageRequest.of(0, 10)
        val companyList = listOf(
            Company( "Company1", "Address1","010-2222-1111", "Introduction1", listOf(Specialty.VIOLENCE,Specialty.FRAUD)),
            Company( "Company2", "Address2","010-2222-1111", "Introduction2", listOf(Specialty.VIOLENCE))
        )
        val page = PageImpl(companyList, pageReq, companyList.size.toLong())



        beforeContainer {
            every { reader.findAllPagination(pageReq) } returns page
            every { service.register(any()) } returns CompanyResp(company)

            service = CompanyService(
                manager,
                reader,
                fileService,
            )
        }
            When("회사 등록 요청을 하면"){
                val result = service.register(request)

                Then("회사 객체가 생성 된다"){
                    result shouldBe company
                }
            }
    }


})
