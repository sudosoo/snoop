package com.api.pladder.domain.repository.contract;

import com.api.pladder.domain.entity.contract.Victim;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.UUID;

public interface VictimContentRepository extends BaseRepository<Victim, UUID> {
}
