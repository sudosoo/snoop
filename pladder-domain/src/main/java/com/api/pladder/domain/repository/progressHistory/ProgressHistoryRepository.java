package com.api.pladder.domain.repository.progressHistory;

import com.api.pladder.domain.entity.progressHistory.Progress;
import com.api.pladder.domain.repository.common.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProgressHistoryRepository extends BaseRepository<Progress, UUID> {
    Page<Progress> paginationByContractId(UUID id, Pageable pageable);
}
