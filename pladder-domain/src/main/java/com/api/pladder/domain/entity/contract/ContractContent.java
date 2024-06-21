package com.api.pladder.domain.entity.contract;

import com.api.pladder.core.utils.date.DateUtil;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Embeddable
public class ContractContent {
    //사건장소
    private String incidentLocation;
    //사건시간
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime incidentTime = DateUtil.INSTANCE.getDEFAULT_DATE_TIME();


    public ContractContent(String incidentLocation, LocalDateTime incidentTime) {
        this.incidentLocation = incidentLocation;
        this.incidentTime = incidentTime;
    }

}
