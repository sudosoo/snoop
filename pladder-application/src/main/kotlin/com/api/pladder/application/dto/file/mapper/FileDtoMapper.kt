package com.api.pladder.application.dto.file.mapper

import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.domain.entity.file.File

object FileDtoMapper {
    fun toEntity(request : FileRequest) : File {
        return File(request.fileName, request.type,request.targetId,request.targetType,request.writerId)
    }

}