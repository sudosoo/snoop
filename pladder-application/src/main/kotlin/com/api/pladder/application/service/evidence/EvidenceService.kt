package com.api.pladder.application.service.evidence

import com.api.pladder.application.dto.contractContent.evidence.EvidenceImageResp
import com.api.pladder.application.dto.contractContent.evidence.EvidenceResp
import com.api.pladder.application.dto.contractContent.evidence.RegisterEvidenceReq
import com.api.pladder.application.dto.contractContent.mapper.EvidenceDtoMapper
import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.application.service.evidence.manage.EvidenceManage
import com.api.pladder.application.service.evidence.reader.EvidenceReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.utils.file.FileUtils
import com.api.pladder.domain.entity.evidence.Evidence
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
    val fileUtils: FileUtils
){
    fun registerFile(req: RegisterEvidenceReq): EvidenceResp {
        val fileType = FileType.fromPrefix(req.type)
        val evidenceOpt = reader.findByContractIdAndFileType(UUID.fromString(req.contractId),fileType)

        if (evidenceOpt.isEmpty()) {
            val obj = EvidenceDtoMapper.toEntity(req)
            for (file in req.file) {
                val extension = fileUtils.getExtension(file.originalFilename!!)
                val type = determineFileType(extension)

                val fileReq = FileReq(type, file)
                val fileEntity = fileService.save(fileReq)
                manage.registerFile(obj, fileEntity)
            }
            return EvidenceResp(obj)

        } else {
            val evidence = evidenceOpt.get()
            for (file in req.file) {
                val extension = fileUtils.getExtension(file.originalFilename!!)
                val type = determineFileType(extension)
                val fileReq = FileReq(type, file)
                val fileEntity = fileService.save(fileReq)
                evidence.addFile(fileEntity)
            }
            return EvidenceResp(evidence)
        }
    }

    private fun determineFileType(extension: String): FileType {
        return when (extension.lowercase()) {
            "m4a", "mp3" -> FileType.EVIDENCE_AUDIO
            else -> FileType.EVIDENCE_IMAGE
        }
    }


    fun getEvidence(contractId :String) : List<EvidenceImageResp> {
        val evidencies = reader.findByContractId(UUID.fromString(contractId))
        val imageEvidences = mutableListOf<Evidence>()
        val audioEvidences = mutableListOf<Evidence>()

        for (evidence in evidencies) {
            when (evidence.type) {
                FileType.EVIDENCE_IMAGE -> imageEvidences.add(evidence)
                FileType.EVIDENCE_AUDIO -> audioEvidences.add(evidence)
                else -> {}
            }
        }

    }


}