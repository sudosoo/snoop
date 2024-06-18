package com.api.pladder.presentation.controller

import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TestController(
    val service: FileService
) : ResponseEntityCreation, AuthDataProvider {

}