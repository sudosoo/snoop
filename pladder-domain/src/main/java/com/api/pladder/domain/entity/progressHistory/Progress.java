package com.api.pladder.domain.entity.progressHistory;


import com.api.pladder.core.utils.date.DateUtil;
import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.contract.Contract;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="pd_progress_history")
public class Progress extends BaseEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID progressId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime recordingTime = DateUtil.INSTANCE.getDEFAULT_DATE_TIME();

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contract_id")
    private Contract contract;

    public Progress(String content) {
        this.content = content;
    }

    public void addContract (Contract contract){
        this.contract = contract;
        contract.addProgress(this);
    }

    public void updateContent(String content){
        this.content = content;
    }


}