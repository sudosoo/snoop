package com.api.pladder.domain.entity.file;

import com.api.pladder.core.exception.NotFoundException;
import com.api.pladder.domain.entity.file.enums.FileExtension;
import com.api.pladder.domain.entity.file.enums.FileTargetType;
import com.api.pladder.domain.entity.file.enums.FileType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity(name = "pd_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    @Column(updatable = false, nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    private FileTargetType targetType;

    private UUID targetId;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    private UUID writerId;

    private File(String fileName, FileType type, UUID targetId, FileTargetType targetType,UUID writerId) {
        this.fileName = fileName;
        this.fileType = type;
        this.targetId = targetId;
        this.targetType = targetType;
        this.writerId = writerId;
    }

    public static File of(String id, FileType type, UUID targetId,FileTargetType targetType,UUID writerId) {
        return new File(id, type, targetId, targetType,writerId);
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
