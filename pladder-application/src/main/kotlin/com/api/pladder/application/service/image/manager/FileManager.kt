package com.api.pladder.application.service.image.manager

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.image.File
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.FileRepository
import org.springframework.stereotype.Component

@Component
class FileManager(
    val repository: FileRepository
) : JpaService<File, String> {
    override var jpaRepository: BaseRepository<File, String> = repository



}