package com.api.pladder.presentation.controller

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.image.request.ImageReq
import com.api.pladder.application.service.image.ImageService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import org.springframework.http.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/api")
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
                ,authReq = getAuthReq()
            )
        )
    }

    @GetMapping(value = ["/detective/image/{imageId}"])
    fun findImage(@PathVariable companyId: String): ResponseEntity<ByteArray> {
        val imageData = service.getImage(UUID.fromString(companyId))
        // Content-Type 설정
        val contentType: MediaType = MediaType.IMAGE_JPEG
        val headers = HttpHeaders()
        headers.contentType = contentType

        // Content-Disposition 설정 (파일 다운로드를 위한 설정)
        headers.contentDisposition = ContentDisposition
            .builder("inline")
            .filename("$imageData.${contentType.subtype}")
            .build()

        return ResponseEntity(imageData, headers, HttpStatus.OK)
    }


}