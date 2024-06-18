package com.api.pladder.domain.repository.image;

import com.api.pladder.domain.entity.file.File;
import com.api.pladder.domain.entity.file.enums.FileTargetType;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends BaseRepository<File, String> {

    List<File> findByTargetIdAndTargetType(UUID targetId, FileTargetType type);
}
