package com.api.pladder.domain.entity.company;

import com.api.pladder.domain.entity.user.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Company {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String companyName;
    private String bossName;
    private String address;
    private String phoneNumber;
    private CompanyStatus userStatus = CompanyStatus.WAIT_TING;

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

    public void approval() {
        this.userStatus = CompanyStatus.ACTIVE;
    }
}
