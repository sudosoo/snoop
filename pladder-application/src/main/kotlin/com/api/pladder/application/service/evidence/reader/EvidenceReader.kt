package com.api.pladder.application.service.evidence.reader

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.evidence.Evidence
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.evidence.EvidenceRepository
import org.springframework.stereotype.Component
import java.util.*
@Component
class EvidenceReader(
    val repository: EvidenceRepository
) : JpaService<Evidence, UUID> {

    override var jpaRepository: BaseRepository<Evidence, UUID> = repository
}