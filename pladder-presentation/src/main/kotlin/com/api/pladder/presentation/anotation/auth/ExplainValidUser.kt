package com.api.pladder.presentation.anotation.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse


@Operation(
    summary = "(IUS007) 회원 유효성 검증",
    description = "비밀번호를 입력하여 로그인 비밀번호를 한번 더 확인합니다./회원탈퇴 , 비밀번호 변경시 사용됩니다."
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
annotation class ExplainValidUser()
