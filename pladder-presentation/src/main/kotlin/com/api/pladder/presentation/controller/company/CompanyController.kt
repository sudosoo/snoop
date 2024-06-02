package com.api.pladder.presentation.controller.company

import com.api.pladder.application.service.company.CompanyService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "Company", description = "회사 관련 API")
@RequestMapping("/api")
class CompanyController(
    val service: CompanyService

) {


}