package com.api.pladder.application.service.evidence

import com.api.pladder.application.service.evidence.manage.EvidenceManage
import com.api.pladder.application.service.evidence.reader.EvidenceReader
import com.api.pladder.application.service.file.FileService
import org.springframework.stereotype.Service

@Service
class EvidenceService (
    val manage : EvidenceManage,
    val reader : EvidenceReader,
    val fileService: FileService
){

}