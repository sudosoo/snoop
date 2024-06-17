package com.api.pladder.application.service.evidence

import com.api.pladder.application.dto.contractContent.evidence.RegisterEvidenceReq
import com.api.pladder.application.dto.contractContent.mapper.EvidenceDtoMapper
import com.api.pladder.application.dto.file.request.FileReq
import com.api.pladder.application.service.evidence.manage.EvidenceManage
import com.api.pladder.application.service.evidence.reader.EvidenceReader
import com.api.pladder.application.service.file.FileService
import com.api.pladder.core.exception.NotFoundException
import com.api.pladder.domain.entity.file.enums.FileType
import org.springframework.stereotype.Service
import java.util.*

@Service
class EvidenceService (
    val manage : EvidenceManage,
    val reader : EvidenceReader,
    val fileService: FileService
){

    fun registerImage(req : RegisterEvidenceReq){
        try {
        val evidence = reader.getEvidenceByTitle(UUID.fromString(req.contractId),req.title)
            for (file in req.file){
                val fileReq = FileReq(FileType.EVIDENCE_IMAGE,file)
                val fileEntity = fileService.save(fileReq)
                evidence.addFile(fileEntity)
            }
        }catch (e:NotFoundException) {
            for (file in req.file){
                val fileReq = FileReq(FileType.EVIDENCE_IMAGE,file)
                val fileEntity = fileService.save(fileReq)
                val evidence = EvidenceDtoMapper.toEntity(req)
                manage.registerFile(evidence,fileEntity)
            }
        }

    }
    fun registerAudio(req : RegisterEvidenceReq) {
        try {
            val evidence = reader.getEvidenceByTitle(UUID.fromString(req.contractId), req.title)
            for (file in req.file) {
                val fileReq = FileReq(FileType.EVIDENCE_AUDIO, file)
                val fileEntity = fileService.save(fileReq)
                evidence.addFile(fileEntity)
            }
        } catch (e: NotFoundException) {
            for (file in req.file) {
                val fileReq = FileReq(FileType.EVIDENCE_AUDIO, file)
                val fileEntity = fileService.save(fileReq)
                val evidence = EvidenceDtoMapper.toEntity(req)
                manage.registerFile(evidence, fileEntity)
            }
        }
    }

}