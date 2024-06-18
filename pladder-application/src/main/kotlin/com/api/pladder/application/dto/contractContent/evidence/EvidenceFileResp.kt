package com.api.pladder.application.dto.contractContent.evidence

import com.api.pladder.domain.entity.evidence.Evidence

class EvidenceFileResp(evidence: Evidence , files: List<ByteArray>) {
    val evidenceId = evidence.id.toString()
    val title = evidence.title
    var files: List<ByteArray> = files
}
