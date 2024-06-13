package com.api.pladder.application.dto.image.request

import com.api.pladder.domain.entity.image.enums.ImageTargetType
import com.api.pladder.domain.entity.image.enums.ImageType
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class ImageReq(
    @Schema(description = "이미지 종류" ,
        example = "PR - 프로필 , " +
                "CL - 회사로고 , " +
                "ID - 신분증 , " +
                "LR - 지자체 신고증 , " +
                "DL - 탐정 면허증 , " +
                "BU - 사업자 등록증" +
                "ED - 증거사진")
    val type : ImageType,
    @Schema(description = "파일" , example = "첨부 파일")
    val file: MultipartFile,
    val targetId : String? = null,
    val targetType : ImageTargetType? = null,
    val fileSize: Long = 0
){
    lateinit var fileName : String
    constructor(type: String, file: MultipartFile, targetId: String , targetType: ImageTargetType) : this(
        type = ImageType.fromPrefix(type.uppercase()),
        file = file,
        fileSize = file.size
    )
    fun updateFileName(fileName: String){
        this.fileName = fileName
    }

}