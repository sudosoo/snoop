package com.api.pladder.application.dto.contractContent.evidence

import com.api.pladder.application.dto.file.response.FileResp
import com.api.pladder.domain.entity.evidence.Evidence

class EvidenceFileResp(evidence: Evidence , files: List<FileResp>) {
    val evidenceId = evidence.id.toString()
    val title = evidence.title
    var files: List<FileResp> = files
}
