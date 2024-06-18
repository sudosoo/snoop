package com.api.pladder.application.service.evidence

import com.api.pladder.application.dto.contractContent.evidence.EvidenceFileResp
import com.api.pladder.application.dto.contractContent.evidence.EvidenceResp
import com.api.pladder.application.dto.contractContent.evidence.RegisterEvidenceReq
import com.api.pladder.application.dto.contractContent.mapper.EvidenceDtoMapper
import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.application.service.contract.ContractService
import com.api.pladder.application.service.evidence.manage.EvidenceManage
import com.api.pladder.application.service.evidence.reader.EvidenceReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.enums.UserType
import com.api.pladder.core.exception.AccessDeniedException
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.core.utils.file.FileUtils
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class EvidenceService (
    val manage : EvidenceManage,
    val reader : EvidenceReader,
    val fileService: FileService,
    val fileUtils: FileUtils,
    val contractService: ContractService
){
    fun register(req: RegisterEvidenceReq, authObj: AuthUserObject): EvidenceResp {
        if (authObj.userType == UserType.CUSTOMER) {
            throw AccessDeniedException("탐정만 등록 가능합니다.")
        }
        val entity = EvidenceDtoMapper.toEntity(req)
        val evidence = manage.save(entity)

        req.file.forEach { file ->
            val type = FileType.fromPrefix(req.type)
            val fileReq = FileReq(
                type=type,
                file=file,
                targetId=evidence.id,
                targetType= FileTargetType.EVIDENCE)
            fileService.save(fileReq)
        }
        return EvidenceResp(evidence)
    }

    fun getFiles(evidenceId: String , authObj: AuthUserObject): EvidenceFileResp {
        val evidence = reader.findById(UUID.fromString(evidenceId))
        contractService.validateOwner(evidence.contractId, authObj)
        val byteArrays = fileService.findByTargetIdAndTargetType(evidence.id,FileTargetType.EVIDENCE)

        return EvidenceFileResp(evidence, byteArrays)
    }


}