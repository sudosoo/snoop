package com.api.pladder.presentation.controller.image

import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "이미지", description ="이미지 관련 API")
@RestController
@RequestMapping("/api")
class ImageController (
    val service : FileService
) : AuthDataProvider, ResponseEntityCreation {

}