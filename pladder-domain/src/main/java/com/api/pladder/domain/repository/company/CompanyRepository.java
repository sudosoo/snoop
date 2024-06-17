package com.api.pladder.domain.repository.company;

import com.api.pladder.domain.entity.company.Company;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends BaseRepository<Company, UUID> {
    Optional<Company> findByDetectiveId(UUID detectiveId);
}
