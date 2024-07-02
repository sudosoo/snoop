package com.api.pladder.domain.entity.company;

import com.api.pladder.core.exception.InvalidRequestException;
import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.enums.ConfirmStatus;
import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.entity.user.Detective;
import com.api.pladder.domain.entity.user.enums.Specialty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="pd_company")
@NoArgsConstructor
public class Company extends BaseEntity {
    @Column(unique = true)
    private String companyName;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Specialty.class)
    @CollectionTable(name="company_specialty", joinColumns = @JoinColumn(name="company_id"))
    private List<Specialty> specialization = new ArrayList<>();

    private String introduction;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Detective detective;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    private List<Contract> contracts = new ArrayList<>();

    @Enumerated(value = STRING)
    private ConfirmStatus confirmStatus = ConfirmStatus.WAITING;

    public Company(String companyName,
                   String address,
                   String phoneNumber ,
                   String introduction,
                   List<Specialty> specialization,
                   Detective detective
    ) throws InvalidRequestException {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.specialization.addAll(specialization);
        this.confirmStatus = ConfirmStatus.CONFIRMED;
        addDetective(detective);
    }

    private Company (String companyName, String address, String phoneNumber, String introduction, List<Specialty> specialization){
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.specialization.addAll(specialization);
        this.confirmStatus = ConfirmStatus.CONFIRMED;
    }

    private void addDetective(Detective detective) {
        this.detective = detective;
        detective.addCompany(this);
    }

    public Company testOf(String companyName, String address, String phoneNumber, String introduction, List<Specialty> specialization){
        return new Company( companyName, address, phoneNumber, introduction, specialization);
    }

    public void updateInfo(String introduction, List<Specialty> specialization) {
        this.introduction = introduction;
        this.specialization.clear();
        this.specialization.addAll(specialization);
    }



}
