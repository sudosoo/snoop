package com.api.pladder.presentation.anotation.contract

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Operation(
    summary = "(ICC004) 계약서 상세 내용 삭제",
)
@ApiResponse(
    responseCode = "200",
    description = "성공",
    content = [Content(
        mediaType = "application/json",
        examples = [ExampleObject(value = """
{
  "status": "OK",
  "message": "Your request has been processed successfully."
}
                """)])]
)
annotation class ExplainDeleteContractContent()
