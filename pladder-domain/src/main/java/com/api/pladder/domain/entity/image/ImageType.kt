package com.api.pladder.domain.entity.image

import com.api.pladder.domain.entity.user.enums.UserType


enum class ImageType (
    val prefix: String,
    val description: String,
    val selectPermissions: List<UserType> = listOf(), //조회 권한
    val deletePermissions: List<UserType> = listOf(), //삭제 권한
    val createPermissions: List<UserType> = listOf(), //등록 권한
    ) {
    BUSINESS_REGISTRATION_CERTIFICATE(
        prefix = "BU",
        description = "사업자 등록증",
        createPermissions = listOf(UserType.DETECTIVE),
        selectPermissions = listOf(UserType.CUSTOMER,UserType.DETECTIVE, UserType.ADMIN),
        deletePermissions = listOf(UserType.DETECTIVE, UserType.ADMIN)
     ),
    DETECTIVE_LICENSE(
        prefix = "DL",
        description = "영업 신고증",
        createPermissions = listOf(UserType.DETECTIVE),
        selectPermissions = listOf(UserType.CUSTOMER,UserType.DETECTIVE, UserType.ADMIN),
        deletePermissions = listOf(UserType.DETECTIVE, UserType.ADMIN)
    ),
    LOCAL_GOVERNMENT_REPORT(
        prefix = "LR",
        description = "지자체 신고증",
        createPermissions = listOf(UserType.DETECTIVE),
        selectPermissions = listOf(UserType.CUSTOMER,UserType.DETECTIVE, UserType.ADMIN),
        deletePermissions = listOf(UserType.DETECTIVE, UserType.ADMIN)
    ),
    TAX_DECLARATION_CERTIFICATE(
        prefix = "ID",
        description = "신분증",
        createPermissions = listOf(UserType.DETECTIVE),
        selectPermissions = listOf(UserType.DETECTIVE, UserType.ADMIN),
        deletePermissions = listOf(UserType.DETECTIVE, UserType.ADMIN)
    ),
    COMPANY_LOGO(
        prefix = "MA",
        description = "회사로고",
        createPermissions = listOf(UserType.DETECTIVE),
        selectPermissions = listOf(UserType.CUSTOMER,UserType.DETECTIVE, UserType.ADMIN),
        deletePermissions = listOf(UserType.DETECTIVE, UserType.ADMIN)
    ),
    PROFILE(
        prefix = "RE",
        description = "프로필사진",
        createPermissions = listOf(UserType.DETECTIVE),
        selectPermissions = listOf(UserType.CUSTOMER,UserType.DETECTIVE, UserType.ADMIN),
        deletePermissions = listOf(UserType.DETECTIVE, UserType.ADMIN)
    )
}
