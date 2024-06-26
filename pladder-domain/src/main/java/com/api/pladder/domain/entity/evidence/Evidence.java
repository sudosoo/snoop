package com.api.pladder.domain.entity.evidence;

import com.api.pladder.domain.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "pd_evidence")
public class Evidence extends BaseEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private UUID contractId;
    private String title;

    public Evidence(UUID contractId) {
        this.contractId = contractId;
    }

}
