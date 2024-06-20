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
import org.springframework.format.annotation.DateTimeFormat;

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
    private String clientName;
    private String clientPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<Progress> progress = new ArrayList<>();
    //분야
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    //계약서 상세 내용
    private UUID contractContentId;
    //선금
    private int advanceDeposit;
    //수임료
    private int pee;
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
    //신청서 작성일
    private LocalDateTime applyDate = LocalDateTime.now();
    //분야 (사고 , 범죄 , 사생활)
    @Enumerated(EnumType.STRING)
    private Specialty contractField = Specialty.NONE;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.WAITING;
    //가해자
    private UUID perpetratorId;
    //피해자
    private UUID victimId;
    //사건장소
    private String incidentLocation;
    //사건시간
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime incidentTime = DateUtil.INSTANCE.getDEFAULT_DATE_TIME();

    public void addProgress(Progress history ){
        this.progress.add(history);
    }

    public Contract(UUID customerId, Company company, int advanceDeposit, int pee, String purpose, String description, String clientName, String clientPhone) {
        this.customerId = customerId;
        this.advanceDeposit = advanceDeposit;
        this.pee = pee;
        this.purpose = purpose;
        this.description = description;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        addCompany(company);
    }

    public void addCompany(Company company) {
        this.company = company;
        company.getContracts().add(this);
    }

    public void updatePeriod(String startPeriod, String endPeriod) {
        this.startPeriod = LocalDate.parse(startPeriod);
        this.endPeriod = LocalDate.parse(endPeriod);
    }
    public void accept() {
        this.status = ContractStatus.APPLY;
    }

    public void contentUpdate(Specialty contractField, String incidentLocation, String incidentTime){
        this.contractField = contractField;
        this.incidentLocation = incidentLocation;
        this.incidentTime = LocalDateTime.parse(incidentTime);
    }
}
