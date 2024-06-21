package com.api.pladder.application.dto.contract.mapper

import com.api.pladder.application.dto.contract.evidence.RegisterEvidenceReq
import com.api.pladder.domain.entity.evidence.Evidence
import java.util.*

object EvidenceDtoMapper {
    fun toEntity(request : RegisterEvidenceReq) : Evidence{
        return Evidence(UUID.fromString(request.contractId))}


}