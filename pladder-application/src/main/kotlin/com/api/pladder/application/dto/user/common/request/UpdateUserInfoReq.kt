package com.api.pladder.application.dto.user.common.request

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateUserInfoReq (
    @Schema(description = "닉네임" , example = "김대충")
    val email:String? = null,
    @Schema(description = "이메일" , example = "abc@pladder.com")
    val nickname:String? = null,
    @Schema(description = "비밀번호" , example = "abc1q2w3e")
    val passwd: String,
    @Schema(description = "변경 할 비밀번호" , example = "def1q2w3e")
    var reqUpdatePasswd: String
){
    fun updateConvertEncodingPasswd(convertEncodingPasswd : String) {
        this.reqUpdatePasswd = convertEncodingPasswd
    }
}