package com.api.pladder.domain.repository.image;

import com.api.pladder.domain.entity.image.File;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends BaseRepository<File, String> {

    Optional<File> findByTargetId(UUID companyId);
}
