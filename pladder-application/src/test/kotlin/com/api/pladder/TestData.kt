package com.api.pladder

import com.api.pladder.core.enums.UserType
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.user.Customer
import com.api.pladder.domain.entity.user.Detective
import java.util.*

object TestData {
    /**
     * authReq
     */
    val authReqCS10000001 = AuthUserObject(
        userId = UUID.fromString("feeb066f-a118-4dfd-a141-eb8d6f31b8b1"),
        userType = UserType.CUSTOMER,
    )

    val authReqBS10000001 = AuthUserObject(
        userId = UUID.fromString("f6744202-f40f-4ce7-b00f-1a8d10456454"),
        userType = UserType.DETECTIVE,
    )

    val authReqAdmin = AuthUserObject(
        userId = UUID.fromString("82eb81c2-7df9-4e47-9362-c71c6ac78f60"),
        userType = UserType.ADMIN,
    )

    /**
     * user
     */
    val detective = Detective()
        .createTestEntity(
            UUID.fromString("f6744202-f40f-4ce7-b00f-1a8d10456454"),
            "abc@naver.com",
            "abc1q2w3e",
            "010-1234-5678")


    val customer = Customer().createTestEntity(
        UUID.fromString("feeb066f-a118-4dfd-a141-eb8d6f31b8b1"),
        "안녕",
        "abc1q2w3e")

    /**
     * company
     */
    val rewardReqForSave1 = RewardReq(
        target = "COFFEE",
        quantity = null,
        type = RewardType.DISCOUNT,
        discountRate = 90,
        content = null
    )

    val rewardReqForSave2 = RewardReq(
        target = "TEA",
        quantity = 1,
        type = RewardType.GIFT,
        discountRate = null,
        content = null
    )

    /**
     * contract
     */
    val missionReqForSave1 = MissionReq(
        content = null,
        target = "COFFEE",
        quantity = 1,
        type = MissionType.NORMAL,
    )

    val missionReqForSave2 = MissionReq(
        content = "매장 방문 5회",
        target = null,
        quantity = null,
        type = MissionType.FREE,
    )

    val missionForSave = Mission(
        questId = "QS20000001",
        quantity = 5,
        content = null,
        target = "TEA",
        type = MissionType.NORMAL,
    )

    /**
     * contractContent
     */
    val rewardReqForSave1 = RewardReq(
        target = "COFFEE",
        quantity = null,
        type = RewardType.DISCOUNT,
        discountRate = 90,
        content = null
    )

    val rewardReqForSave2 = RewardReq(
        target = "TEA",
        quantity = 1,
        type = RewardType.GIFT,
        discountRate = null,
        content = null
    )

    /**
     * perpetrator
     */
    val rewardReqForSave1 = RewardReq(
        target = "COFFEE",
        quantity = null,
        type = RewardType.DISCOUNT,
        discountRate = 90,
        content = null
    )

    val rewardReqForSave2 = RewardReq(
        target = "TEA",
        quantity = 1,
        type = RewardType.GIFT,
        discountRate = null,
        content = null
    )

    /**
     * victim
     */
    val rewardReqForSave1 = RewardReq(
        target = "COFFEE",
        quantity = null,
        type = RewardType.DISCOUNT,
        discountRate = 90,
        content = null
    )

    val rewardReqForSave2 = RewardReq(
        target = "TEA",
        quantity = 1,
        type = RewardType.GIFT,
        discountRate = null,
        content = null
    )


}