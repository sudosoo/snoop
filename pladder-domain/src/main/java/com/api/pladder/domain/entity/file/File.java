package com.api.pladder.domain.entity.file;

import com.api.pladder.core.exception.NotFoundException;
import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.file.enums.FileExtension;
import com.api.pladder.domain.entity.file.enums.FileTargetType;
import com.api.pladder.domain.entity.file.enums.FileType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "pd_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx_target_type_id_file_type", columnList = "targetType, targetId, fileType")
})
public class File extends BaseEntity {
    @Id
    @Column(updatable = false, nullable = false)
    private String fileName;

    private UUID targetId;

    @Enumerated(EnumType.STRING)
    private FileTargetType targetType;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    private UUID writerId;

    public File(String fileName, FileType type, UUID targetId, FileTargetType targetType,UUID writerId) {
        this.fileName = fileName;
        this.fileType = type;
        this.targetId = targetId;
        this.targetType = targetType;
        this.writerId = writerId;
    }


    public FileExtension getExtension() throws NotFoundException {
        int dot = this.fileName.lastIndexOf('.');
        if (dot == -1 || dot == this.fileName.length() - 1) {
            throw new NotFoundException("이미지 확장자를 찾을 수 없습니다.");
        }
        String stringExtension = this.fileName.substring(dot + 1);
        return FileExtension.extractExtension(stringExtension);
    }


}
