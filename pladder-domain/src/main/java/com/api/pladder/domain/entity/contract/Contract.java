package com.api.pladder.domain.entity.contract;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.Company;
import com.api.pladder.domain.entity.contract.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Contract extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    private UUID contractContentId;
    private String advance;
    private String pee;
    private String purpose;
    private String solution;
    private LocalDate startPeriod;
    private LocalDate endPeriod = LocalDate.of(9999,12,31);
    private String returnFormat;
    private String description;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.WAITING;


}
