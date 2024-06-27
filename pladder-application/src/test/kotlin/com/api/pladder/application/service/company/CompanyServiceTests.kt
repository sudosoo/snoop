package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.response.CompanyResp
import com.api.pladder.application.dto.company.response.UpdateCompanyResp
import com.api.pladder.application.service.company.CompanyTestData.company1
import com.api.pladder.application.service.company.CompanyTestData.company2
import com.api.pladder.application.service.company.CompanyTestData.company3
import com.api.pladder.application.service.company.CompanyTestData.request
import com.api.pladder.application.service.company.CompanyTestData.request2
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.application.service.file.FileService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("local")
class CompanyServiceTests  : BehaviorSpec({
    val manager : CompanyManager = mockk()
    val reader : CompanyReader = mockk()
    val fileService: FileService = mockk()

    var service = CompanyService(manager, reader, fileService)

    Given("회사 등록 관련 TEST"){

        val pageReq = PageRequest.of(0, 10)
        val companyList = listOf(
            company1,
            company2,
            company3
           )
        val page = PageImpl(companyList, pageReq, companyList.size.toLong())



        beforeContainer {
            every { reader.findAllPagination(pageReq) } returns page
            every { manager.register(any()) } returns company1

            every { reader.findById(any())} returns company2
            every { manager.register(any()) } returns company1

            every { manager.updateInfo(any(),any())} returns company1

            service = CompanyService(
                manager,
                reader,
                fileService,
            )
        }
        When("회사 등록 요청을 하면"){
                val result = service.register(request)
                verify {manager.register(request)}

                Then("회사 객체가 생성 된다"){
                    result.id shouldBe CompanyResp(company1).id
                }
            }

        When("탐정 사무소 조회를 하면"){
            val result = service.getList(pageReq)
            verify {reader.findAllPagination(pageReq)}

            Then("탐정 사무소 리스트가 생성 된다"){
                result.content.size shouldBe 3
            }
        }

        When("회사 정보 수정 요청을 하면"){
            val result = service.updateInfo(request2)
            verify {manager.updateInfo(company2,request2)}

            Then("회사 객체가 수정 된다"){
                result.introduce shouldBe UpdateCompanyResp(company1).introduce
            }
        }

    }


})
