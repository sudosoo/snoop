package com.api.pladder.domain.entity.company;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.enums.ConfirmStatus;
import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.entity.user.enums.SpecializeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Company extends BaseEntity {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true)
    private String companyName;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialize_status")
    @ElementCollection(targetClass = SpecializeStatus.class)
    @CollectionTable(name = "specialize_status", joinColumns = @JoinColumn(name = "company_id"))
    private List<SpecializeStatus> specialization = new ArrayList<>();

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    private List<Contract> contracts = new ArrayList<>();

    @Enumerated(value = STRING)
    private ConfirmStatus confirmStatus = ConfirmStatus.WAIT_TING;

    public Company(String companyName,
                   String address,
                   String phoneNumber ,
                   String introduction) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
    }
    public void appendSpecialize(List<SpecializeStatus> specializeStatus){
        this.specialization.addAll(specializeStatus);
    }

    public void update(String companyName, String address, String phoneNumber) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public void updateConfirmStatus(ConfirmStatus confirmStatus) {
        this.confirmStatus = confirmStatus;
    }


}
