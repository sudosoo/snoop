package com.api.pladder.application.dto.contractContent.evidence

import com.api.pladder.domain.entity.evidence.Evidence

class EvidenceResp(evidence : Evidence){
    val contractId = evidence.contractId
    val title = evidence.id
}