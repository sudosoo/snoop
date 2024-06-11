package com.api.pladder.domain.entity.image;

import com.api.pladder.core.exception.NotFoundException;
import com.api.pladder.domain.entity.image.enums.ImageExtension;
import com.api.pladder.domain.entity.image.enums.ImageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "pd_image")
public class Image {
    @Id
    @Column(updatable = false, nullable = false)
    private String imageId;
    private UUID companyId;
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private Long size;

    public Image(String imageId, UUID companyId, ImageType type, Long size) {
        this.imageId = imageId;
        this.companyId = companyId;
        this.type = type;
        this.size = size;
    }

    public static Image of(String id, UUID writerId, ImageType type, Long size) {
        return new Image(id, writerId, type, size);
    }

    public ImageExtension getExtension() throws NotFoundException {
        int dot = this.imageId.lastIndexOf('.');
        if (dot == -1 || dot == this.imageId.length() - 1) {
            throw new NotFoundException("이미지 확장자를 찾을 수 없습니다.");
        }
        String stringExtension = this.imageId.substring(dot + 1);
        return ImageExtension.extractExtension(stringExtension);
    }


}
