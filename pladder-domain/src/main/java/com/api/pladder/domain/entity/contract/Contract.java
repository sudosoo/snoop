package com.api.pladder.domain.entity.contract;

import com.api.pladder.core.utils.date.DateUtil;
import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.Company;
import com.api.pladder.domain.entity.contract.enums.ContractStatus;
import com.api.pladder.domain.entity.progressHistory.Progress;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name="pd_contract")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;

    private UUID customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<Progress> progress = new ArrayList<>();

    //계약서 상세 내용
    private UUID contractContentId;
    //선금
    private String advanceDeposit;
    //수임료
    private String pee;
    //목적 ( 고소 , 신고)
    private String purpose;
    //조사결과 (사진 , 문서 , 동영상)
    private UUID conclusionId;
    //시작일
    private LocalDate startPeriod = DateUtil.INSTANCE.getDEFAULT_DATE();
    //계약종료일
    private LocalDate endPeriod = DateUtil.INSTANCE.getDEFAULT_DATE();
    //해결 포맷 (사진 , 문서 , 동영상)
    private String description;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.WAITING;

    public void addProgress(Progress history ){
        this.progress.add(history);
    }

    public Contract(UUID customerId, Company company, String advanceDeposit, String pee, String purpose, String description) {
        this.customerId = customerId;
        this.company = company;
        this.advanceDeposit = advanceDeposit;
        this.pee = pee;
        this.purpose = purpose;
        this.description = description;
    }

    public void updatePeriod(String startPeriod, String endPeriod) {
        this.startPeriod = LocalDate.parse(startPeriod);
        this.endPeriod = LocalDate.parse(endPeriod);
    }

}
