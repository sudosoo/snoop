package com.api.pladder.domain.entity.progressHistory;


import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.contract.Contract;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgressHistory extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime recordingTime;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;


}