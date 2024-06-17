package com.api.pladder.application.dto.file.request

import com.api.pladder.domain.entity.image.enums.FileExtension
import com.api.pladder.domain.entity.image.enums.FileTargetType
import com.api.pladder.domain.entity.image.enums.FileType
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
                "ED - 증거사진")
    val type : FileType,
    @Schema(description = "파일" , example = "첨부 파일")
    val file: MultipartFile
){
    lateinit var targetId : String
    lateinit var targetType : FileTargetType

    lateinit var extension: FileExtension
    lateinit var fileName : String

    constructor(type: String, file: MultipartFile) : this(
        type = FileType.fromPrefix(type.uppercase()),
        file = file
    )
    fun updateFileName(fileName: String){
        this.fileName = fileName
    }

}