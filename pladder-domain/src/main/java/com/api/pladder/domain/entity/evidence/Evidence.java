package com.api.pladder.domain.entity.evidence;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Evidence {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false)
    private UUID id;
    private UUID contractId;
    //File ID
    private String fileName;
    private String content;


    public Evidence(UUID contractId, String fileName, String content) {
        this.contractId = contractId;
        this.fileName = fileName;
        this.content = content;
    }

}
