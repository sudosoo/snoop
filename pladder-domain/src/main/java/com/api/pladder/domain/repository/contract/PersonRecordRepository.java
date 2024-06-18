package com.api.pladder.domain.repository.contract;

import com.api.pladder.domain.entity.contract.PersonRecord;
import com.api.pladder.domain.entity.contract.enums.PersonStatus;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRecordRepository extends BaseRepository<PersonRecord, UUID> {
    Optional<PersonRecord> findByIdAndStatus(UUID id, PersonStatus status);
}
