package com.api.pladder.application.dto.contract.person.request

import com.api.pladder.domain.entity.contract.enums.Gender
import io.swagger.v3.oas.annotations.media.Schema

class UpdatePersonReq (
    @Schema(description="인물 ID")
    var personId: String?= null,
    @Schema(description="변경 이름")
    var name: String?= null,
    @Schema(description="변경 나이")
    var age: Int = 0,
    @Schema(description="변경 성별")
    var gender :Gender? = null,
    @Schema(description="변경 관계")
    var relationship: String?= null,
    @Schema(description="변경 직장 주소")
    var workplaceAddr: String?= null,
    @Schema(description="변경 인상 착의")
    var impression: String?= null,
    @Schema(description="변경 주거지")
    var residenceAddr: String?= null
)
