package com.api.pladder.presentation.controller.company

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.presentation.anotation.company.ExplainGetCompanyList
import com.api.pladder.presentation.anotation.company.ExplainRegisterCompany
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "Company", description = "회사 관련 API")
@RequestMapping("/api")
class CompanyController(
    val service: CompanyService
): AuthDataProvider , ResponseEntityCreation {
    @ExplainRegisterCompany
    @PostMapping(value = ["/detective/company"])
    fun register(req:RegisterCompanyReq): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.register(
                req = req ,
                authObj = getAuthReq()))
    }


    @ExplainGetCompanyList
    @PostMapping(value = ["/open/company"])
    fun getList(@RequestParam(defaultValue = "0") page : Int,
                @RequestParam(defaultValue = "10") size : Int,
    ){
        service.getList(PageRequest.of(page, size))
    }

}