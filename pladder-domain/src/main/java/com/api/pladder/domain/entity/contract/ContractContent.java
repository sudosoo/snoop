package com.api.pladder.domain.entity.contract;

import com.api.pladder.core.utils.date.DateUtil;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Embeddable
public class ContractContent {
    //시작일
    private LocalDate startPeriod = DateUtil.INSTANCE.getDEFAULT_DATE();
    //계약종료일
    private LocalDate endPeriod = DateUtil.INSTANCE.getDEFAULT_DATE();

    //사건장소
    private String incidentLocation;

    //사건시간
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime incidentTime = DateUtil.INSTANCE.getDEFAULT_DATE_TIME();

    private String description;

    public ContractContent(LocalDate startPeriod, LocalDate endPeriod, String incidentLocation, LocalDateTime incidentTime,String description) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.incidentLocation = incidentLocation;
        this.incidentTime = incidentTime;
        this.description = description;
    }

    public void updateDescription(String Content){
        this.description = Content;
    }
}
