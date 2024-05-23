package com.api.pladder.application.dto.image.request

import com.api.pladder.application.dto.image.enums.ImageType
import org.springframework.web.multipart.MultipartFile

class ImageReq(
    val type : ImageType,
    val file: MultipartFile
)