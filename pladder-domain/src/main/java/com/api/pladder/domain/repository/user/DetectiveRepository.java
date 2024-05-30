package com.api.pladder.domain.repository.user;

import com.api.pladder.domain.entity.user.Detective;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface DetectiveRepository extends BaseRepository<Detective, UUID> {
    Optional<Detective> findByEmail(String email);
}
