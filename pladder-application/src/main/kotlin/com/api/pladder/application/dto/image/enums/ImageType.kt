package com.api.pladder.application.dto.image.enums

import com.api.pladder.application.auth.enums.UserType
import com.api.pladder.domain.entity.user.enums.UserStatus

enum class ImageType (
    val prefix: String,
    val description: String
    ) {
    BUSINESS_REGISTRATION_CERTIFICATE(
        prefix = "BU",
        description = "사업자 등록증",
     ),
    DETECTIVE_LICENSE(
        prefix = "DL",
        description = "영업 신고증",
    ),
    LOCAL_GOVERNMENT_REPORT(
        prefix = "LR",
        description = "지자체 신고증",
    ),
    TAX_DECLARATION_CERTIFICATE(
        prefix = "ID",
        description = "신분증",
    ),
    COMPANY_LOGO(
        prefix = "MA",
        description = "회사로고",
    ),
    PROFILE(
        prefix = "RE",
        description = "프로필사진",
    )
}
