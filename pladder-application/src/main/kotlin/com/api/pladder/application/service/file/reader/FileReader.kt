package com.api.pladder.application.service.file.reader

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.FileRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class FileReader (
    val repository: FileRepository
) : JpaService<File, String> {
    override var jpaRepository: BaseRepository<File, String> = repository

    fun findByTargetIdAndTargetType(targetId: UUID, targetType: FileTargetType, pageRequest: PageRequest): Page<File> {
        return repository.findByTargetIdAndTargetType(targetId,targetType,pageRequest)
    }

    fun findByTargetIdAndTargetTypeAndFileType(targetId: UUID, targetType: FileTargetType, fileType: FileType): Optional<File> {
        return repository.findByTargetIdAndTargetTypeAndFileType(targetId,targetType,fileType)
    }

}

