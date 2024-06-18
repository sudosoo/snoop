package com.api.pladder.application.service.file.reader

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.FileRepository
import org.hibernate.tool.schema.TargetType
import org.springframework.stereotype.Component
import java.util.*

@Component
class FileReader (
    val repository: FileRepository
) : JpaService<File, String> {
    override var jpaRepository: BaseRepository<File, String> = repository

    fun findByTargetIdAndType(targetId: UUID,targetType: FileTargetType): List<File> {
        return repository.findByTargetIdAndType(targetId,targetType)
        }

}

