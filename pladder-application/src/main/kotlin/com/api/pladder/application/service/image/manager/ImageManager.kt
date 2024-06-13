package com.api.pladder.application.service.image.manager

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.image.Image
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.image.ImageRepository
import org.springframework.stereotype.Component

@Component
class ImageManager(
    val repository: ImageRepository
) : JpaService<Image, String> {
    override var jpaRepository: BaseRepository<Image, String> = repository



}