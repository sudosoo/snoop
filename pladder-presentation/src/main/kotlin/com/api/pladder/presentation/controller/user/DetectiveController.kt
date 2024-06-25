package com.api.pladder.presentation.controller.user

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.user.detective.request.RegisterDetectiveCareerReq
import com.api.pladder.application.service.user.detective.DetectiveService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.user.ExplainRegisterDetectiveCareer
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "탐정전용", description ="탐정 전용 API")
@RestController
@RequestMapping("/api/detective")
class DetectiveController (
    val service : DetectiveService
) : AuthDataProvider, ResponseEntityCreation {
    @ExplainRegisterDetectiveCareer
    @PutMapping(value = ["/registerCareer"])
    fun registerCareer(@RequestBody request : List<RegisterDetectiveCareerReq>) : ResponseEntity<BaseResp> {
        return getRespEntity(service.registerCareer(
            request = request,
            authObj = getAuthReq()))
    }

}