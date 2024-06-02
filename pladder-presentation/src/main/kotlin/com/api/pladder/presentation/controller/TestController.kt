package com.api.pladder.presentation.controller

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.application.service.image.ImageService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/test")
class TestController (
    val service : ImageService
): ResponseEntityCreation, AuthDataProvider {

    @Transactional(rollbackFor = [Exception::class])
    @PostMapping(value = [ "/detective/image"], consumes = ["multipart/form-data"])
    fun save(
        @RequestParam(name="file") file: MultipartFile,
        @RequestParam(name="type") type: String
    ): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.save(
                req = ImageReq(type = type, file = file)
            //,authReq = getAuthReq()
            )
        )
    }



}