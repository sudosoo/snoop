package com.api.pladder.application.service.evidence

import com.api.pladder.application.dto.contract.evidence.EvidenceFileResp
import com.api.pladder.application.dto.contract.evidence.EvidenceResp
import com.api.pladder.application.dto.contract.evidence.RegisterEvidenceReq
import com.api.pladder.application.dto.contract.mapper.EvidenceDtoMapper
import com.api.pladder.application.dto.file.request.FileRequest
import com.api.pladder.application.service.evidence.manage.EvidenceManage
import com.api.pladder.application.service.evidence.reader.EvidenceReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.obj.AuthUserObject
import com.api.pladder.domain.entity.file.enums.FileTargetType
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class EvidenceService (
    val manage : EvidenceManage,
    val reader : EvidenceReader,
    val fileService: FileService,
){
    fun register(request: RegisterEvidenceReq, authObj: AuthUserObject): EvidenceResp {
        val entity = EvidenceDtoMapper.toEntity(request)
        val evidence = manage.save(entity)

        request.file.forEach { file ->
            val type = FileType.fromPrefix(request.type)
            val fileRequest = FileRequest(
                type=type,
                file=file,
                targetId=evidence.id,
                targetType= FileTargetType.EVIDENCE,
                writerId=authObj.userId!!)
            fileService.save(fileRequest)
        }

        return EvidenceResp(evidence)
    }

    fun getContents(evidenceId: String, authObj: AuthUserObject,pageRequest: PageRequest): EvidenceFileResp {
        val evidence = reader.findById(UUID.fromString(evidenceId))
        val files = fileService.findByTargetIdAndTargetType(evidence.id,FileTargetType.EVIDENCE,pageRequest)

        return EvidenceFileResp(evidence, files)
    }

    fun delete(evidenceId: String, authObj: AuthUserObject){
        return manage.deleteById(UUID.fromString(evidenceId))
    }

}