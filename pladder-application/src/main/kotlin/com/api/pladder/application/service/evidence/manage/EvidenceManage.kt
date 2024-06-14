package com.api.pladder.application.service.evidence.manage

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.evidence.Evidence
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.evidence.EvidenceRepository
import java.util.*

class EvidenceManage(
    val repository: EvidenceRepository
) : JpaService<Evidence, UUID> {

    override var jpaRepository: BaseRepository<Evidence, UUID> = repository
}