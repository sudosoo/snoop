package com.api.pladder.application.dto.file.mapper

import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.domain.entity.file.File

object FileDtoMapper {
    fun toEntity(request : FileReq) : File {
        return File.of(request.fileName, request.type,request.targetId,request.targetType)
    }

}