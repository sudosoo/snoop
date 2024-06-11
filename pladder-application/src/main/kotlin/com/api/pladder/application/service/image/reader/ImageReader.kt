package com.api.pladder.application.service.image.reader

import com.api.pladder.application.service.common.jpa.JpaService
import com.api.pladder.domain.entity.image.Image
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.ImageRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class ImageReader (
    val repository: ImageRepository
) :JpaService<Image, String>{
    override var jpaRepository: BaseRepository<Image, String> = repository

    fun findByWriterId(companyId: UUID): Image {
        return repository.findByCompanyId(companyId).orElseThrow{
            throw IllegalArgumentException("Image not found")
        }
    }
}