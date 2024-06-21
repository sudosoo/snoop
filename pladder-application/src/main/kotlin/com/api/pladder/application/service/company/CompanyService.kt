package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.company.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CompanyService (
    val manager : CompanyManager,
    val reader : CompanyReader
){
    fun register(request: RegisterCompanyReq, authObj: AuthUserObject) {
        manager.register(request,authObj.userId!!)
    }

    fun getList(pageReq: PageRequest) : Page<Company> {
        return reader.findAllPagination(pageReq)
    }
    
    fun updateInfo(request : UpdateCompanyInfoReq){
        manager.updateInfo(request)
    }
    
    

}