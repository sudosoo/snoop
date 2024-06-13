package com.api.pladder.application.dto.image.mapper

import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.domain.entity.image.Image
import java.util.*

object ImageDtoMapper {

    fun toEntity(req : ImageReq) : Image {
        return Image.of(req.fileName,req.type, UUID.fromString(req.targetId),req.targetType, req.file.size)
    }

}