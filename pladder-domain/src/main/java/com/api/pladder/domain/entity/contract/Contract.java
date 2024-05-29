package com.api.pladder.domain.entity.contract;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.Company;
import com.api.pladder.domain.entity.contract.enums.ContractStatus;
import com.api.pladder.domain.entity.progressHistory.ProgressHistory;
import com.api.pladder.domain.entity.user.Customer;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contract")
    private List<ProgressHistory> progressHistory = new ArrayList<>();

    //계약서 상세 내용
    private UUID contractContentId;
    //선금
    private String prepayment;
    //수임료
    private String pee;
    //목적 ( 고소 , 신고)
    private String purpose;
    //조사결과 (사진 , 문서 , 동영상)
    private UUID conclusionId;
    //시작일
    private LocalDate startPeriod;
    //계약종료일
    private LocalDate endPeriod = LocalDate.of(9999,12,31);
    //해결 포맷 (사진 , 문서 , 동영상)
    private String returnFormat;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.WAITING;

}
