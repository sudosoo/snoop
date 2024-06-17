package com.api.pladder.domain.entity.file;

import com.api.pladder.core.exception.NotFoundException;
import com.api.pladder.domain.entity.evidence.Evidence;
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
    private UUID targetId;
    @Enumerated(EnumType.STRING)
    private FileTargetType targetType;
    @Enumerated(EnumType.STRING)
    private FileType type;
    private FileExtension extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evidence_id")
    private Evidence evidence;

    private File(String fileName, FileType type, UUID targetId, FileTargetType targetType,FileExtension extension) {
        this.fileName = fileName;
        this.type = type;
        this.targetId = targetId;
        this.targetType = targetType;
        this.extension = extension;
    }

    public static File of(String id, FileType type, UUID targetId, FileTargetType targetType,FileExtension extension) {
        return new File(id, type, targetId,targetType,extension);
    }

    public FileExtension getExtension() throws NotFoundException {
        int dot = this.fileName.lastIndexOf('.');
        if (dot == -1 || dot == this.fileName.length() - 1) {
            throw new NotFoundException("이미지 확장자를 찾을 수 없습니다.");
        }
        String stringExtension = this.fileName.substring(dot + 1);
        return FileExtension.extractExtension(stringExtension);
    }

    public void appendEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

}
