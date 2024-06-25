package com.api.pladder.core.utils.file

import com.api.pladder.core.enums.UserType
import org.springframework.stereotype.Component
import java.util.*

@Component
class
FileUtils {
    fun getExtension(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

    fun getCategory(ext: String): String {
        return when (ext.lowercase(Locale.getDefault())) {
            "jpg", "jpeg", "png", "gif" -> "IM"
            "m4a", "mp3" -> "AU"
            "pdf" -> "PF"
            else -> throw IllegalArgumentException("지원하지 않는 확장자 입니다.: $ext")
        }
    }

    fun getUserTypeFromFileName(fileName: String): UserType {
        val filePrefixLength = 2
        val userRoleLength = 2
        val userRole = fileName.substring(filePrefixLength, filePrefixLength + userRoleLength)

        return when (userRole) {
            "CU" -> UserType.CUSTOMER
            "DE" -> UserType.DETECTIVE
            "SU" -> UserType.ADMIN
            else -> throw IllegalArgumentException("Unsupported user role: $userRole")
        }
    }


}