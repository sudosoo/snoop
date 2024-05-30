package com.api.pladder.domain.repository.user;

import com.api.pladder.domain.entity.user.Admin;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends BaseRepository<Admin, UUID> {
    Optional<Admin> findByEmail(String email);

}
