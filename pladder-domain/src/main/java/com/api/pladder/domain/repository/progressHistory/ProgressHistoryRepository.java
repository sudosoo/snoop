package com.api.pladder.domain.repository.progressHistory;

import com.api.pladder.domain.entity.progressHistory.Progress;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ProgressHistoryRepository extends BaseRepository<Progress, UUID> {

    List<Progress> findAllByContractId(UUID id);
}
