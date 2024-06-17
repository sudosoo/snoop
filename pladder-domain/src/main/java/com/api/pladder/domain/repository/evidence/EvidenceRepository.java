package com.api.pladder.domain.repository.evidence;

import com.api.pladder.domain.entity.evidence.Evidence;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface EvidenceRepository extends BaseRepository<Evidence, UUID> {

    Optional<Evidence> findByContractIdAndTitle(UUID contractId,String title);
}
