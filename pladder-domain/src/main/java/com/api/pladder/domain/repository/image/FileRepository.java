package com.api.pladder.domain.repository.image;

import com.api.pladder.domain.entity.file.File;
import com.api.pladder.domain.entity.file.enums.FileTargetType;
import com.api.pladder.domain.entity.file.enums.FileType;
import com.api.pladder.domain.repository.common.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends BaseRepository<File, String> {
    Page<File> findByTargetIdAndTargetType(UUID targetId, FileTargetType type, PageRequest pageRequest);
    List<File> findByTargetIdAndTargetType(UUID targetId, FileTargetType type);

    Optional<File> findByTargetIdAndTargetTypeAndFileType(UUID targetId, FileTargetType type, FileType fileType);

    void deleteAllByTargetIdAndTargetType(UUID targetId, FileTargetType type);

}
