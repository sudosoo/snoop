package com.api.pladder.application.service.evidence

import com.api.pladder.application.dto.contract.evidence.EvidenceFileResp
import com.api.pladder.application.dto.contract.evidence.EvidenceResp
import com.api.pladder.application.dto.contract.evidence.RegisterEvidenceReq
import com.api.pladder.application.dto.contract.mapper.EvidenceDtoMapper
import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.application.service.evidence.manage.EvidenceManage
import com.api.pladder.application.service.evidence.reader.EvidenceReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.obj.AuthUserObject
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
){
    fun register(req: RegisterEvidenceReq, authObj: AuthUserObject): EvidenceResp {
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

    fun getContents(evidenceId: String, authObj: AuthUserObject): EvidenceFileResp {
        val evidence = reader.findById(UUID.fromString(evidenceId))
        val files = fileService.findByTargetIdAndTargetType(evidence.id,FileTargetType.EVIDENCE)

        return EvidenceFileResp(evidence, files)
    }

    fun delete(evidenceId: String, authObj: AuthUserObject){
        return manage.deleteById(UUID.fromString(evidenceId))
    }

}