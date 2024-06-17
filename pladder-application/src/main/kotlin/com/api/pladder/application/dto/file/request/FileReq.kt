package com.api.pladder.application.dto.file.request

import com.api.pladder.domain.entity.file.enums.FileType
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class FileReq(
    @Schema(description = "이미지 종류" ,
        example = "PR - 프로필 , " +
                "CL - 회사로고 , " +
                "ID - 신분증 , " +
                "LR - 지자체 신고증 , " +
                "DL - 탐정 면허증 , " +
                "BU - 사업자 등록증" +
                "EI - 증거 사진" +
                "EO - 증거 오디오")
    val type : FileType,
    @Schema(description = "파일" , example = "첨부 파일")
    val file: MultipartFile
){
    constructor(type: String, file: MultipartFile) : this(
        type = FileType.fromPrefix(type.uppercase()),
        file = file
    )
}