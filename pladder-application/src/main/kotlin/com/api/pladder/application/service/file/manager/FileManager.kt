package com.api.pladder.application.service.file.manager

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.FileRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class FileManager(
    val repository: FileRepository
) : JpaService<File, String> {
    override var jpaRepository: BaseRepository<File, String> = repository

    fun deleteAllByTargetIdAndTargetType(targetId: UUID, targetType: FileTargetType ) {
        repository.deleteAllByTargetIdAndTargetType(targetId,targetType)
    }

    fun deleteAll(files: List<File>) {
        repository.deleteAll(files)
    }

}