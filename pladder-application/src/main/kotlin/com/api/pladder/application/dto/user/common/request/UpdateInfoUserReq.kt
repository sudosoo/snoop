package com.api.pladder.application.dto.user.common.request

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateInfoUserReq (
    @Schema(description = "변경할 핸드폰 번호" , example = "010-0000-0000")
    val phoneNumber: String,
    @Schema(description = "변경 할 닉네임" , example = "띵동")
    val nickName: String
)



