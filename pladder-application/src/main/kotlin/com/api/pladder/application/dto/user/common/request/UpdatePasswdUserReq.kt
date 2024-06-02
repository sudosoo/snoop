package com.api.pladder.application.dto.user.common.request

import io.swagger.v3.oas.annotations.media.Schema

data class UpdatePasswdUserReq (
    @Schema(description = "이메일" , example = "abc@gmail.com")
    val email:String,
    @Schema(description = "비밀번호" , example = "abc1q2w3e")
    val passwd: String,
    @Schema(description = "변경 할 비밀번호" , example = "def1q2w3e")
    val reqUpdatePasswd: String
)