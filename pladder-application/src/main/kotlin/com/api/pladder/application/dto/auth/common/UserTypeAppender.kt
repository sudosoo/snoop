package com.api.pladder.application.dto.auth.common

import com.api.pladder.domain.entity.user.enums.UserType

interface UserTypeAppender{
    fun setTypeGeneratedCustomer() : UserType {
        return UserType.CUSTOMER
    }
    fun setTypeGeneratedDetective() : UserType{
        return UserType.DETECTIVE
    }
}
