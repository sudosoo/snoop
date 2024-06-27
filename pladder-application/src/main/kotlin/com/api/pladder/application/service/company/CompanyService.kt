package com.api.pladder.application.service.company

import com.api.pladder.application.dto.company.request.RegisterCompanyReq
import com.api.pladder.application.dto.company.request.UpdateCompanyInfoReq
import com.api.pladder.application.dto.company.request.UpdateCompanyProfileImageReq
import com.api.pladder.application.dto.company.response.CompanyListResp
import com.api.pladder.application.dto.company.response.CompanyResp
import com.api.pladder.application.dto.company.response.UpdateCompanyResp
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.dto.file.response.FileResp
import com.api.pladder.application.service.company.manager.CompanyManager
import com.api.pladder.application.service.company.reader.CompanyReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyService (
    val manager : CompanyManager,
    val reader : CompanyReader,
    val fileService: FileService
){
    fun register(request: RegisterCompanyReq): CompanyResp {
        return CompanyResp(manager.register(request))
    }

    fun getList(pageReq: PageRequest): Page<CompanyListResp> {
        val pagination = reader.findAllPagination(pageReq)
        val models = pagination.content.map { CompanyListResp(it) }
        return PageImpl(models, pageReq, pagination.totalElements)
    }
    
    fun updateInfo(request : UpdateCompanyInfoReq): UpdateCompanyResp {
        val company = reader.findById(UUID.fromString(request.companyId))
        return UpdateCompanyResp(manager.updateInfo(company,request))
    }

    fun updateProfileImage(request: UpdateCompanyProfileImageReq, authObj: AuthUserObject): CompanyResp{
        val fileRequest = FileRequest(
            type = FileType.PROFILE,
            file = request.image,
            targetId = UUID.fromString(request.companyId),
            targetType = FileTargetType.COMPANY,
            writerId = authObj.userId!!,
            userType = authObj.userType)
        fileService.updateOrCreateProfileImage(fileRequest)
        return CompanyResp(request.companyId)
    }

    fun getProfileImage(companyId:String, authObj: AuthUserObject) : FileResp {
        return fileService.getProfileImage(UUID.fromString(companyId), FileTargetType.COMPANY,authObj.userType)
    }
    
    

}