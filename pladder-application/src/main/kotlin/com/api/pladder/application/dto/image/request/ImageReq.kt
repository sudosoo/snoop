package com.api.pladder.application.dto.image.request

import com.api.pladder.domain.entity.image.enums.ImageType
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

class ImageReq(
    @Schema(description = "이미지 종류" ,
        example = "PROFILE - 프로필 , " +
                "COMPANY_LOGO - 회사로고 , " +
                "ID_CARD - 신분증 , " +
                "LOCAL_GOVERNMENT_REPORT - 지자체 신고증 , " +
                "DETECTIVE_LICENSE - 탐정 면허증 , " +
                "BUSINESS_REGISTRATION_CERTIFICATE - 사업자 등록증")
    val type : ImageType,
    @Schema(description = "파일" , example = "첨부 파일")
    val file: MultipartFile,
    var fileSize: Long = 0
){
    constructor(type: String, file: MultipartFile) : this(
        type = ImageType.fromPrefix(type.uppercase()),
        file = file,
        fileSize = file.size
    )

}