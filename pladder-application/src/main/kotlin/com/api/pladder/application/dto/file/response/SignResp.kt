package com.api.pladder.application.dto.file.response

import io.swagger.v3.oas.annotations.media.Schema

class SignResp(
    fileResp: FileResp , writerType: String
){
    @Schema(description = "파일 이름")
    val fileName : String = fileResp.fileName
    @Schema(description = "파일 생성자의 유저 타입")
    val userType: String = writerType
    @Schema(description = "이미지")
    val byteArray: ByteArray = fileResp.byteArray
}