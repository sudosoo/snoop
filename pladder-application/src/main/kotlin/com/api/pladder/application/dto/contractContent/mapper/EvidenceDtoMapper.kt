package com.api.pladder.application.dto.contractContent.mapper

import com.api.pladder.application.dto.contractContent.evidence.RegisterEvidenceReq
import com.api.pladder.domain.entity.evidence.Evidence
import java.util.*

object EvidenceDtoMapper {
    fun toEntity(req : RegisterEvidenceReq) : Evidence{
        return Evidence(UUID.fromString(req.contractId))}


}