package com.api.pladder.domain.entity.company;

import com.api.pladder.domain.entity.contract.Contract;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Company {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String companyName;
    private String bossName;
    private String phoneNumber;
    private String address;
    private String specialization;
    private String bossProfile;
    private String introduction;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Contract> contracts;

    //TODO :을 기준으로 [0]연차:[1]경력사항 총 년차 계산
    @ElementCollection
    @CollectionTable(name = "company_career", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "year")
    @Column(name = "description")
    private Map<Integer,String> career;

    @Enumerated(value = STRING)
    private ConfirmStatus confirmStatus = ConfirmStatus.WAIT_TING;

    public Company(String companyName, String bossName, String address, String phoneNumber) {
        this.companyName = companyName;
        this.bossName = bossName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void update(String companyName, String bossName, String address, String phoneNumber) {
        this.companyName = companyName;
        this.bossName = bossName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public void updateConfirmStatus(ConfirmStatus confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public int totalCareer() {
        return career.keySet().stream().mapToInt(Integer::intValue).sum();
    }
}
