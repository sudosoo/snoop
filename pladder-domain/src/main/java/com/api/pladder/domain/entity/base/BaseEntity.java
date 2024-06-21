package com.api.pladder.domain.entity.base;

import com.api.pladder.domain.entity.common.DateTimePattern;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    @DateTimeFormat(pattern = DateTimePattern.STANDARD_PATTERN)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(pattern = DateTimePattern.STANDARD_PATTERN)
    private LocalDateTime updatedAt;

}

