package com.api.pladder.domain.entity.company;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.enums.ConfirmStatus;
import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.entity.user.enums.Specialty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Entity(name="pd_company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false)
    private UUID companyId;

    @Column(unique = true)
    private String companyName;

    private UUID detectiveId;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Specialty.class)
    @CollectionTable(name="Filed", joinColumns = @JoinColumn(name="company_id"))
    private List<Specialty> specialization = new ArrayList<>();

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    private List<Contract> contracts = new ArrayList<>();

    @Enumerated(value = STRING)
    private ConfirmStatus confirmStatus = ConfirmStatus.WAIT_TING;


    public Company(String companyName,
                   String address,
                   String phoneNumber ,
                   String introduction,
                   List<Specialty> specialization,
                   UUID detectiveId) {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.specialization.addAll(specialization);
        this.detectiveId = detectiveId;
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
