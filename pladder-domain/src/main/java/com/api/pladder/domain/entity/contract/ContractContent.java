package com.api.pladder.domain.entity.contract;

import com.api.pladder.domain.entity.user.enums.Filed;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name="pd_contract_content")
public class ContractContent{
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    //계약 분야
    private Filed contractField = Filed.NONE;
    //가해자
    private UUID perpetratorId;
    //피해자
    private UUID victimId;
    //사건장소
    private String incidentLocation;
    //사건시간
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime incidentTime;
}