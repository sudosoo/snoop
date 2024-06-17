package com.api.pladder.core.utils.file

import org.springframework.stereotype.Component
import java.util.*

@Component
class FileUtils {
    fun getExtension(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

    fun getFileName(fileName: String): String {
        return fileName.substring(0, fileName.lastIndexOf("."))
    }

    fun getCategory(ext: String): String {
        return when (ext.lowercase(Locale.getDefault())) {
            "jpg", "jpeg", "png", "gif" -> "IM"
            "m4a", "mp3" -> "AU"
            "pdf" -> "PF"
            else -> throw IllegalArgumentException("지원하지 않는 확장자 입니다.: $ext")
        }
    }


}