package com.api.pladder.domain.entity.evidence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

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
    private String title;

    public Evidence(UUID contractId) {
        this.contractId = contractId;
    }

}
