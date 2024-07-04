package com.api.pladder.domain.entity.event;

import com.api.pladder.domain.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.tool.schema.TargetType;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "pd_events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {
    private UUID targetId;
    private TargetType targetType;
    private String payload;
    private int successCount;
    private boolean isComplete;



}


