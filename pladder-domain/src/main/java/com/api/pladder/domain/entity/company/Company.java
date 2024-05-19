package com.api.pladder.domain.entity.company;

import com.api.pladder.domain.entity.company.enums.ConfirmStatus;
import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.entity.user.Boss;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Company {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true)
    private String companyName;
    private UUID bossId;
    private String phoneNumber;
    private String address;
    private String specialization;
    private String introduction;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "company")
    private List<Contract> contracts = new ArrayList<>();
    @Enumerated(value = STRING)
    private ConfirmStatus confirmStatus = ConfirmStatus.WAIT_TING;

    public Company(String companyName, String address, String phoneNumber) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
