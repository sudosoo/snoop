package com.api.pladder.domain.entity.image;

import com.api.pladder.core.exception.NotFoundException;
import com.api.pladder.domain.entity.image.enums.ImageExtension;
import com.api.pladder.domain.entity.image.enums.ImageTargetType;
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
    private UUID targetId;
    @Enumerated(EnumType.STRING)
    private ImageTargetType targetType;
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private Long size;

    private Image(String imageId,ImageType type, UUID targetId, ImageTargetType targetType , Long size) {
        this.imageId = imageId;
        this.type = type;
        this.targetId = targetId;
        this.targetType = targetType;
        this.size = size;
    }

    public static Image of(String id,ImageType type, UUID targetId,ImageTargetType targetType, Long size) {
        return new Image(id, type, targetId,targetType, size);
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
