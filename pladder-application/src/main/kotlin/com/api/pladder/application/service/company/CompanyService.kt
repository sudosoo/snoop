package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import org.springframework.stereotype.Service

@Service
class CompanyService (
    val manager : CompanyManager,
    val reader : CompanyReader
){
    fun register(req: RegisterCompanyReq, authObj: AuthUserObject) {
        if(authObj.userType != UserType.DETECTIVE){
            throw AccessDeniedException("탐정만 회사를 등록할 수 있습니다.")
        }
        manager.register(req)
    }

}