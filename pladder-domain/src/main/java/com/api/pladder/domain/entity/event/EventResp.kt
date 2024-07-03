package com.api.pladder.domain.entity.event

import com.api.pladder.core.enums.ErrorStatus
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description="이벤트 단건 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
class EventResp(
    data : Any?,
    errorStatus : ErrorStatus = ErrorStatus.OK,
    errMessage : String? = null,
) {
    @Schema(description = "데이터")
    var data : Any? = data

    @Schema(description = "응답 상태")
    var status : String = errorStatus.name

    @Schema(description = "응답 메시지")
    var message : String = errorStatus.message

    @Schema(description = "오류 메세지")
    var errMessage : String? = errMessage
}