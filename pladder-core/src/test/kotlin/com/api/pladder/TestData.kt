package com.api.pladder

import com.api.pladder.core.enums.UserType
import com.api.pladder.core.obj.AuthUserObject
import java.util.*


object TestData {
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

}