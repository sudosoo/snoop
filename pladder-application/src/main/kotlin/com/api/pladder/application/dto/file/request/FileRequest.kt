package com.api.pladder.application.dto.file.request

import com.api.pladder.core.enums.UserType
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile
import java.util.*

class FileRequest(
    @Schema(description = "이미지 종류" ,
        example = "PR - 프로필 , " +
                "DE - 탐정 자격증 , " +
                "AC - 아카데미 수료증 , " +
                "HI - 건강보험 자격득실 확인서 , " +
                "BU - 사업자 등록증" +
                "EI - 증거 사진" +
                "EO - 증거 오디오"+
                "SN - 서명 이미지")
    val type : FileType,
    @Schema(description = "파일" , example = "첨부 파일")
    val file: MultipartFile,
    val targetId : UUID,
    val targetType : FileTargetType,
    val writerId: UUID,
    val userType: UserType,
){

    lateinit var fileName : String
}