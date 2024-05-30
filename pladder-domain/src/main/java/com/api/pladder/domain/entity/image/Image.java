package com.api.pladder.domain.entity.image;

import com.api.pladder.domain.entity.image.enums.ImageType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="pd_image")
public class Image {
    @Id
    @Column(updatable = false, nullable = false)
    private String id;
    private String writerId;
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private Long size;

    public Image(String id, String writerId, ImageType type, Long size) {
        this.id = id;
        this.writerId = writerId;
        this.type = type;
        this.size = size;
    }

    public static Image of(String id,String writerId, ImageType type, Long size) {
        return new Image(id,writerId,type, size);
    }
}
