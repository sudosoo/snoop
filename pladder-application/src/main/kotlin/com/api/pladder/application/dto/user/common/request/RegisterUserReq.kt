package com.api.pladder.application.dto.user.common.request

import com.api.pladder.application.dto.user.common.UserTypeAppender
import com.api.pladder.domain.entity.user.enums.UserType

data class RegisterUserReq (
    val email:String? = null,
    var passwd:String? = null,
    val phoneNumber: String? = null,
    val nickName: String ? = null
) : UserTypeAppender {
    lateinit var userType: UserType
    fun updateConvertPasswd(convertPass:String) {
        this.passwd = convertPass
    }


}



