package com.api.pladder.application.service

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.contract.request.RegisterContractReq
import com.api.pladder.application.dto.contractContent.person.request.RegisterPersonReq
import com.api.pladder.application.dto.contractContent.request.RegisterContractContentReq
import com.api.pladder.application.dto.contractContent.victim.RegisterVictimReq
import com.api.pladder.application.dto.user.common.request.RegisterUserReq
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.contract.enums.Gender
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective
import com.api.pladder.domain.entity.user.enums.Specialty
import java.util.*


object TestData {
    val detectiveSignup = RegisterUserReq(
        email = "abc@naver.com",
        passwd = "abc1q2w3e",
        phoneNumber = "010-1234-5678",
        userType = UserType.DETECTIVE
    )

    val customerSignup = RegisterUserReq(
        nickName = "안녕",
        passwd = "abc1q2w3e",
        userType = UserType.CUSTOMER
    )

    val adminSignup = RegisterUserReq(
        nickName = "안녕",
        passwd = "abc1q2w3e",
        userType = UserType.ADMIN
    )

    /**
     * authReq
     */
    val testCustomer = AuthUserObject(
        userId = UUID.fromString("feeb066f-a118-4dfd-a141-eb8d6f31b8b1"),
        userType = UserType.CUSTOMER,
    )

    val testDetective = AuthUserObject(
        userId = UUID.fromString("f6744202-f40f-4ce7-b00f-1a8d10456454"),
        userType = UserType.DETECTIVE,
    )

    val testAdmin = AuthUserObject(
        userId = UUID.fromString("82eb81c2-7df9-4e47-9362-c71c6ac78f60"),
        userType = UserType.ADMIN,
    )

    /**
     * user
     */
    val detective = Detective.testEntity(
        UUID.fromString("f6744202-f40f-4ce7-b00f-1a8d10456454"),
        "abc@naver.com",
        "abc1q2w3e",
        "010-1234-5678")



    val customer = Customer.testEntity(
        UUID.fromString("feeb066f-a118-4dfd-a141-eb8d6f31b8b1"),
        "안녕",
        "abc1q2w3e")

    /**
     * company
     */
    val companyReq = RegisterCompanyReq(
        companyName = "PLADDER",
        phoneNumber = "010-0000-0000",
        addr = "서울시 강남구 서초동",
        specialization = listOf(
            Specialty.AFFAIR,
            Specialty.FRAUD,
            Specialty.MISSING),
        introduction = "안녕하세요 실종 전문 탐정 플래더 입니다."
    )
    val companyReq2 = RegisterCompanyReq(
        companyName = "PLADDER2",
        phoneNumber = "010-1234-5664",
        addr = "서울시 강남구 방배동",
        specialization = listOf(
            Specialty.INDUSTRY,
            Specialty.VIOLENCE,
            Specialty.MISSING),
        introduction = "안녕하세요 사기 전문 탐정 플래더 입니다."
    )

    /**
     * contract
     */

    val contract = RegisterContractReq(
        companyId= "CP100001",
        advanceDeposit= 500000,
        pee= 5000000,
        purpose= "고소",
        requestSolution= "고소에 필요한 증거를 수집해 주세요.",
        description= "얼굴이 보이게 잘 찍어주세요."
    )
    val contract2 = RegisterContractReq(
        companyId = "CP100002",
        advanceDeposit = 300000,
        pee = 3000000,
        purpose = "재산 분쟁",
        requestSolution = "재산 분쟁 관련 증거를 수집해 주세요.",
        description = "문서가 잘 보이게 촬영해 주세요."
    )
    val contract3 = RegisterContractReq(
        companyId = "CP100003",
        advanceDeposit = 700000,
        pee = 7000000,
        purpose = "사기",
        requestSolution = "사기 관련 증거를 수집해 주세요.",
        description = "대화 내용이 잘 들리게 녹음해 주세요."
    )

    /**
     * contractContent
     */
    val contractContent1 = RegisterContractContentReq(
        contractId = "CT100001",
        content = "이 계약서는 고소 사건에 대한 것입니다.",
        contractField = Specialty.AFFAIR,
        incidentLocation = "서울시 강남구",
        incidentTime = "2024-06-01 14:00:00"
    )


    val contractContent2 = RegisterContractContentReq(
        contractId = "CT100002",
        content = "이 계약서는 재산 분쟁 사건에 대한 것입니다.",
        contractField = Specialty.INDUSTRY,
        incidentLocation = "부산시 해운대구",
        incidentTime = "2024-06-02 15:30:00"
    )
    val contractContent3 = RegisterContractContentReq(
        contractId = "CT100003",
        content = "이 계약서는 사기 사건에 대한 것입니다.",
        contractField = Specialty.FRAUD,
        incidentLocation = "대전시 서구",
        incidentTime = "2024-06-03 10:00:00"
    )

    /**
     * perpetrator
     */
    val perpetrator = RegisterPersonReq(
        contractId = "feeb066f-a118-4dfd-a141-eb8d6f31b8b1",
        name = "최수민",
        gender = Gender.FEMALE,
        age = 25,
        relationship = "후배",
        workplaceAddr = "인천시 남동구",
        impression = "작고, 밝은 색의 머리를 가지고 있습니다.",
        residenceAddr = "인천시 부평구",
        accompliceId = null
    )
    val perpetrator2 = RegisterPersonReq(
        contractId = "feeb066f-a118-4dfd-a141-eb8d6f31b8b1",
        name = "박민수",
        gender = Gender.MALE,
        age = 30,
        relationship = "친구",
        workplaceAddr = "광주시 북구",
        impression = "슬림하고, 머리가 짧습니다.",
        residenceAddr = "광주시 남구",
        accompliceId = null
    )
    val perpetrator3 = RegisterPersonReq(
        contractId = "CT100003",
        name = "이철수",
        gender = Gender.MALE,
        age = 42,
        relationship = "형제",
        workplaceAddr = "대전시 서구",
        impression = "덩치가 크고, 수염이 있습니다.",
        residenceAddr = "대전시 유성구",
        accompliceId = null
    )

    /**
     * victim
     */
    val victim1 = RegisterVictimReq(
        contractId = "c0d4e8f5-1c2a-42d3-87e8-ace7d6e3a234",
        name = "홍길동",
        relationship = "형",
        phoneNumber = "010-1234-5678"
    )

    val victim2 = RegisterVictimReq(
        contractId = "b1a9e2d8-4f85-4c49-b1b8-fc9d7a6f2e23",
        name = "김영희",
        relationship = "동료",
        phoneNumber = "010-2345-6789"
    )


}