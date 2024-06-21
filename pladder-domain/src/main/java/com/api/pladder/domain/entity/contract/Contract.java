package com.api.pladder.domain.entity.contract;

import com.api.pladder.core.utils.date.DateUtil;
import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.Company;
import com.api.pladder.domain.entity.contract.enums.ContractStatus;
import com.api.pladder.domain.entity.progressHistory.Progress;
import com.api.pladder.domain.entity.user.enums.Specialty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name="pd_contract")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends BaseEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID contractId;

    private UUID customerId;
    private String customerName;
    private String customerPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    //분야
    @Enumerated(EnumType.STRING)
    private Specialty specialty = Specialty.NONE;
    //선금
    private int advanceDeposit;
    //수임료
    private int pee;
    //목적 ( 고소 , 신고)
    private String purpose;

    //해결 포맷 (사진 , 문서 , 동영상)
    private String solutionFormat;
    //계약 내용 ex) 7세 아동 실종 사건
    private String description;

    //시작일
    private LocalDate startPeriod = DateUtil.INSTANCE.getDEFAULT_DATE();
    //계약종료일
    private LocalDate endPeriod = DateUtil.INSTANCE.getDEFAULT_DATE();


    //계약 후 계약서 정보
    @Embedded
    private ContractContent contractContent;

    //신청서 작성 시간
    @Column(updatable = false, nullable = false)
    private LocalDateTime applyDate = LocalDateTime.now();

    //활동상황
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<Progress> progress = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.WAITING;

    //조사결과
    private UUID conclusionId;


    public Contract(Company company,UUID customerId, String customerName, String customerPhone, Specialty specialty, String purpose, String solutionFormat, String description) {
        addCompany(company);
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.specialty = specialty;
        this.purpose = purpose;
        this.solutionFormat = solutionFormat;
        this.description = description;
    }

    private void addCompany(Company company) {
        this.company = company;
        company.getContracts().add(this);
    }

    public void addProgress(Progress history ){
        this.progress.add(history);
    }

    public void updateContent(ContractContent contractContent){
        this.contractContent = contractContent;
    }


    public void updateDescription(String description){
        this.description = description;
    }

    public void apply(int pee , int advanceDeposit, LocalDate startPeriod , LocalDate endPeriod) {
        this.pee = pee;
        this.advanceDeposit = advanceDeposit;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.status = ContractStatus.APPLY;
    }

    public void updateOngoing() {
        this.status = ContractStatus.ONGOING;
    }


}
