package com.api.pladder.presentation.controller.user

import com.api.pladder.application.service.user.detective.DetectiveService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "탐정전용", description ="탐정 전용 API")
@RestController
@RequestMapping("/api/detective")
class CustomerController (
    val service : DetectiveService
) : AuthDataProvider, ResponseEntityCreation {


}