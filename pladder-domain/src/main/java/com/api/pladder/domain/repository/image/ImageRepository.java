package com.api.pladder.domain.repository.image;

import com.api.pladder.domain.entity.image.Image;
import com.api.pladder.domain.repository.common.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends BaseRepository<Image, String> {

    Optional<Image> findByWriterId(UUID writerId);
}
