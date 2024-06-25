package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.application.dto.company.request.UpdateCompanyProfileImageReq
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.dto.file.response.FileResp
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.company.Company
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyService (
    val manager : CompanyManager,
    val reader : CompanyReader,
    val fileService: FileService
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

    fun updateProfileImage(request: UpdateCompanyProfileImageReq, authObj: AuthUserObject){
        val fileRequest = FileRequest(
            type = FileType.PROFILE,
            file = request.image,
            targetId = UUID.fromString(request.companyId),
            targetType = FileTargetType.COMPANY,
            writerId = authObj.userId!!,
            userType = authObj.userType)
        fileService.updateProfileImage(fileRequest)
    }

    fun getProfileImage(companyId:String, authObj: AuthUserObject) : FileResp {
        return fileService.getProfileImage(UUID.fromString(companyId), FileTargetType.COMPANY)
    }
    
    

}