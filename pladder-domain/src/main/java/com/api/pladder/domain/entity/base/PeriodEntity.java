package com.api.pladder.domain.entity.base;

import com.api.pladder.domain.entity.common.DateTimePattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class PeriodEntity {

    @DateTimeFormat(pattern = DateTimePattern.STANDARD_PATTERN)
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = DateTimePattern.STANDARD_PATTERN)
    private LocalDateTime endDateTime;

    private void validatePeriod() {
        if (!endDateTime.isAfter(startDateTime)) {
            throw new IllegalArgumentException("시작 일시는 종료 일시보다 이후일 수 없습니다.");
        }
    }
    public boolean contains(LocalDateTime value) {
        return !value.isBefore(startDateTime) && !value.isAfter(endDateTime);
    }

}
