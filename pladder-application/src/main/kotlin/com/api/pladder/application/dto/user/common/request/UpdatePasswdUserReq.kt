package com.api.pladder.application.dto.user.common.request

import io.swagger.v3.oas.annotations.media.Schema

data class UpdatePasswdUserReq (
    @Schema(description = "변경 할 비밀번호" , example = "def1q2w3e")
    var reqUpdatePasswd: String
){
    fun updateConvertEncodingPasswd(convertEncodingPasswd : String) {
        this.reqUpdatePasswd = convertEncodingPasswd
    }

}