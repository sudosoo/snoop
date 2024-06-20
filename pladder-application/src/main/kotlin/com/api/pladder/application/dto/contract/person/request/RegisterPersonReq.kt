package com.api.pladder.application.dto.contract.person.request

import com.api.pladder.domain.entity.contract.enums.Gender
import com.api.pladder.domain.entity.contract.enums.PersonStatus
import io.swagger.v3.oas.annotations.media.Schema

class RegisterPersonReq (
    @Schema(description="계약서 ID")
    var contractId : String? = null,
    @Schema(description="상태", example="VICTIM, PERPETRATOR, WITNESS, UNKNOWN")
    var status : PersonStatus = PersonStatus.UNKNOWN,
    @Schema(description="이름")
    var name : String? = null,
    @Schema(description="성별")
    var gender : Gender = Gender.UNKNOWN,
    @Schema(description="나이")
    var age : Int = 0,
    @Schema(description="관계")
    var relationship : String? = null,
    @Schema(description="직장 주소")
    var workplaceAddr : String? = null,
    @Schema(description="인상 착의")
    var impression : String? = null,
    @Schema(description="주거지")
    var residenceAddr : String? = null,
    @Schema(description="공범 Id")
    var accompliceId : String? = null
    )