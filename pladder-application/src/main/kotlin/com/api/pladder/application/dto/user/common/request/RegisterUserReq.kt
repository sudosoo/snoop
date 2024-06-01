package com.api.pladder.application.dto.user.common.request

import com.api.pladder.application.dto.user.common.UserTypeAppender
import com.api.pladder.core.enums.UserType

data class RegisterUserReq (
    val email:String? = null,
    var passwd:String? = null,
    val phoneNumber: String? = null,
    val nickName: String ? = null
) : UserTypeAppender {
    lateinit var userType: com.api.pladder.core.enums.UserType

    fun setTypeGeneratedCustomer() : com.api.pladder.core.enums.UserType {
        return com.api.pladder.core.enums.UserType.CUSTOMER
    }
    fun setTypeGeneratedDetective() : com.api.pladder.core.enums.UserType {
        return com.api.pladder.core.enums.UserType.DETECTIVE
    }


    fun updateConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }


}



