package com.api.pladder.presentation.controller

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.image.request.FileReq
import com.api.pladder.application.service.image.FileService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.common.ResponseEntityCreation
import org.springframework.http.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping("/api")
class TestController(
    val service: FileService
) : ResponseEntityCreation, AuthDataProvider {

    @Transactional(rollbackFor = [Exception::class])
    @PostMapping(value = ["/detective/image"], consumes = ["multipart/form-data"])
    fun save(
        @RequestParam(name = "file") file: MultipartFile,
        @RequestParam(name = "type") type: String
    ): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.save(
                req = FileReq(type = type, file = file)
            )
        )
    }

    @GetMapping(value = ["/detective/image/{companyId}"])
    fun findImage(@PathVariable companyId: String): ResponseEntity<ByteArray> {
        val headers = HttpHeaders()

        val imageObj = service.getImage(UUID.fromString(companyId))
        val contentType =
            when (imageObj.imageExtension) {
                "jpg" -> MediaType.IMAGE_JPEG
                "jpeg" -> MediaType.IMAGE_JPEG
                "png" -> MediaType.IMAGE_PNG
                "gif" -> MediaType.IMAGE_GIF
                "pdf" -> MediaType.APPLICATION_PDF
                else -> throw IllegalArgumentException("Unsupported file extension: ${imageObj.imageExtension}")
            }
        headers.contentType = contentType

        // Content-Disposition 설정 (파일 다운로드를 위한 설정)
        headers.contentDisposition = ContentDisposition
            .builder("inline")
            .filename("${imageObj.byte}.${contentType.subtype}")
            .build()

        return ResponseEntity(imageObj.byte, headers, HttpStatus.OK)
    }


}