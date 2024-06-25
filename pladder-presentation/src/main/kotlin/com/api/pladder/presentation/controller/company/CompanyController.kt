package com.api.pladder.presentation.controller.company

import com.api.pladder.application.dto.common.BaseResp
import com.api.pladder.application.dto.company.request.UpdateCompanyProfileImageReq
import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.application.service.company.CompanyService
import com.api.pladder.core.utils.securityProvider.AuthDataProvider
import com.api.pladder.core.utils.securityProvider.AuthDataProvider.Companion.PAGE_SIZE
import com.api.pladder.presentation.anotation.company.ExplainGetCompanyList
import com.api.pladder.presentation.anotation.company.ExplainRegisterCompany
import com.api.pladder.presentation.anotation.company.ExplainRegisterProfileImage
import com.api.pladder.presentation.anotation.company.ExplainUpdateCompanyInfo
import com.api.pladder.presentation.common.ResponseEntityCreation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "탐정 사무소", description = "탐정 사무소 관련 API")
@RequestMapping("/api")
class CompanyController(
    val service: CompanyService
): AuthDataProvider , ResponseEntityCreation {
    @ExplainRegisterCompany
    @PostMapping(value = ["/detective/company"])
    fun register(request:RegisterCompanyReq): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.register(
                request = request ,
                authObj = getAuthReq()))
    }

    @ExplainGetCompanyList
    @GetMapping(value = ["/open/company"])
    fun getList(@RequestParam(defaultValue = "0") page : Int,
    ){
        service.getList(PageRequest.of(page, PAGE_SIZE))
    }

    @ExplainUpdateCompanyInfo
    @PutMapping(value = ["/detective/company"])
    fun updateInfo(request: UpdateCompanyInfoReq): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.updateInfo(
                request = request))
    }

    @ExplainRegisterProfileImage
    @PostMapping(value = ["/detective/profileImage"])
    fun registerImage(request: UpdateCompanyProfileImageReq): ResponseEntity<BaseResp> {
        return getRespEntity(
            service.registerProfileImage(
                request = request))
    }


}