package com.api.pladder.presentation.controller.company

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.core.utils.provider.AuthDataProvider
import com.api.pladder.presentation.anotation.company.ExplainRegisterCompany
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Tag(name = "Company", description = "회사 관련 API")
@RequestMapping("/api/detective/company")
class CompanyController(
    val service: CompanyService
): AuthDataProvider , ResponseEntityCreation {
    @ExplainRegisterCompany
    @PostMapping(value = [])
    fun register(req:RegisterCompanyReq): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.register(
                req = req ,
                authObj = getAuthReq()))
    }

/*
    @ExplainRegisterUser
    @PostMapping(value = ["/detective/register","/customer/register"])
    fun register(request : RegisterUserReq){
        service.signUp(request, getAuthReq())
    }*/

}