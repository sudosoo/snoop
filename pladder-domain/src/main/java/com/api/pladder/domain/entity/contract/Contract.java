package com.api.pladder.domain.entity.contract;

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
    private String clientName;
    private String clientPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    //분야
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    //선금
    private int advanceDeposit;
    //수임료
    private int pee;
    //목적 ( 고소 , 신고)
    private String purpose;
    //조사결과
    private UUID conclusionId;
    //해결 포맷 (사진 , 문서 , 동영상)
    private String description;

    //계약 후 계약서 내용
    @Embedded
    private ContractContent contractContent;

    //신청서 작성 시간
    @Column(updatable = false, nullable = false)
    private LocalDateTime applyDate = LocalDateTime.now();

    //분야 (사고 , 범죄 , 사생활)
    @Enumerated(EnumType.STRING)
    private Specialty contractField = Specialty.NONE;

    //활동상황
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<Progress> progress = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.WAITING;

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

    private void updateContent(String startPeriod, String endPeriod, String incidentLocation, String incidentTime , String description) {
        this.contractContent = new ContractContent(LocalDate.parse(startPeriod), LocalDate.parse(endPeriod), incidentLocation, LocalDateTime.parse(incidentTime),description);
    }

    private void updateDescription(String Content){
        this.contractContent.updateDescription(Content);
    }

    public void accept() {
        this.status = ContractStatus.APPLY;
    }

    public void contentUpdate(Specialty contractField){
        this.contractField = contractField;
        }
}
