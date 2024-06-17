package com.api.pladder.application.service.evidence.manage

import com.api.pladder.application.common.jpa.JpaService
import com.api.pladder.domain.entity.evidence.Evidence
import com.api.pladder.domain.entity.file.File
import com.api.pladder.domain.repository.common.BaseRepository
import com.api.pladder.domain.repository.evidence.EvidenceRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class EvidenceManage(
    val repository: EvidenceRepository
) : JpaService<Evidence, UUID> {

    override var jpaRepository: BaseRepository<Evidence, UUID> = repository

    fun registerFile(evidence: Evidence , file : File){
        evidence.addFile(file)
        save(evidence)
    }
}