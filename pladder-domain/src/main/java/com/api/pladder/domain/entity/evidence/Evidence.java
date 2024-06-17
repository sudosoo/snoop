package com.api.pladder.domain.entity.evidence;

import com.api.pladder.domain.entity.file.File;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "pd_evidence")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Evidence {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false)
    private UUID id;
    private UUID contractId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "evidence")
    private List<File> file = new ArrayList<>();

    private String title;

    public Evidence(UUID contractId, String title) {
        this.contractId = contractId;
        this.title = title;
    }

    public void addFile(File file) {
        this.file.add(file);
        file.appendEvidence(this);
    }

}
