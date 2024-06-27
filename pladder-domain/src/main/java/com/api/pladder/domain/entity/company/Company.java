package com.api.pladder.domain.entity.company;

import com.api.pladder.core.exception.InvalidRequestException;
import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.company.enums.ConfirmStatus;
import com.api.pladder.domain.entity.contract.Contract;
import com.api.pladder.domain.entity.user.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name="pd_company")
@NoArgsConstructor
public class Company extends BaseEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID companyId;

    @Column(unique = true)
    private String companyName;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Specialty.class)
    @CollectionTable(name="company_specialty", joinColumns = @JoinColumn(name="company_id"))
    private List<Specialty> specialization = new ArrayList<>();

    private String introduction;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    private List<Contract> contracts = new ArrayList<>();

    @Enumerated(value = STRING)
    private ConfirmStatus confirmStatus = ConfirmStatus.WAITING;

    public Company(String companyName,
                   String address,
                   String phoneNumber ,
                   String introduction,
                   List<Specialty> specialization
    ) throws InvalidRequestException {
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.specialization.addAll(specialization);
        this.confirmStatus = ConfirmStatus.CONFIRMED;
    }

    private Company (UUID companyId, String companyName, String address, String phoneNumber, String introduction, List<Specialty> specialization){
        this.companyId = companyId;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.specialization.addAll(specialization);
        this.confirmStatus = ConfirmStatus.CONFIRMED;
    }

    public Company testOf(UUID companyId, String companyName, String address, String phoneNumber, String introduction, List<Specialty> specialization){
        return new Company(companyId, companyName, address, phoneNumber, introduction, specialization);
    }

    public void updateInfo(String introduction, List<Specialty> specialization) {
        this.introduction = introduction;
        this.specialization.clear();
        this.specialization.addAll(specialization);
    }


}
