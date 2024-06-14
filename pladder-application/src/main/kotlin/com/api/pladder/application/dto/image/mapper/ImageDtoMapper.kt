package com.api.pladder.application.dto.image.mapper

import com.api.pladder.application.dto.image.request.FileReq
import com.api.pladder.domain.entity.image.File
import java.util.*

object ImageDtoMapper {

    fun toEntity(req : FileReq) : File {
        return File.of(req.fileName,req.type, UUID.fromString(req.targetId),req.targetType, req.file.size)
    }

}