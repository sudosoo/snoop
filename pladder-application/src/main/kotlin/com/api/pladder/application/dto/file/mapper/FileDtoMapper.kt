package com.api.pladder.application.dto.file.mapper

import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.domain.entity.image.File
import java.util.*

object FileDtoMapper {

    fun toEntity(req : FileReq) : File {
        return File.of(req.fileName,req.type, UUID.fromString(req.targetId),req.targetType,req.extension)
    }

}