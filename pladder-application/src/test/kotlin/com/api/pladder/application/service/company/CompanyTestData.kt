package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.user.enums.Specialty
import java.util.*

object CompanyTestData {

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
        UUID.fromString("cbbf62e2-9fa7-478c-971f-77fff3ae2e52"),
        request.name,
        request.addr,
        request.phoneNumber,
        request.introduction,
        request.specialization
    )
    val company2 = Company().testOf(
        UUID.fromString("caf8eada-d9d3-4be1-8645-df8c1b17393b"),
        request2.name,
        request2.addr,
        request2.phoneNumber,
        request2.introduction,
        request2.specialization
    )
    val company3 = Company().testOf(
        UUID.fromString("ecb8e209-8446-4913-872e-b80facc71745"),
        request3.name,
        request3.addr,
        request3.phoneNumber,
        request3.introduction,
        request3.specialization
    )
}