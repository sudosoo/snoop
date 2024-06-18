package com.api.pladder.domain.entity.contract;

import com.api.pladder.core.utils.date.DateUtil;
import com.api.pladder.domain.entity.user.enums.Specialty;
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
@Entity(name="pd_contract_content")
public class ContractContent{
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID contractContentId;
    //계약 분야
    @Enumerated(EnumType.STRING)
    private Specialty contractField = Specialty.NONE;
    private UUID contractId;
    //가해자
    private UUID perpetratorId;
    //피해자
    private UUID victimId;
    //사건장소
    private String incidentLocation;
    //사건시간
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime incidentTime = DateUtil.INSTANCE.getDEFAULT_DATE_TIME();

    public ContractContent(String contractId , Specialty contractField, String incidentLocation, String incidentTime) {
        this.contractId = UUID.fromString(contractId);
        this.contractField = contractField;
        this.incidentLocation = incidentLocation;
        this.incidentTime = LocalDateTime.parse(incidentTime);
    }

    public void update(Specialty contractField, String incidentLocation, String incidentTime){
        this.contractField = contractField;
        this.incidentLocation = incidentLocation;
        this.incidentTime = LocalDateTime.parse(incidentTime);
    }

}