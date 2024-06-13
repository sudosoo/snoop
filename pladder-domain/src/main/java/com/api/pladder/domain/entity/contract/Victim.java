package com.api.pladder.domain.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor()
@Entity(name="pd_victim")
public class Victim {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID victimId;
    private UUID contractId;
    private String name;
    private String relationship = "피해자";
    private String phoneNumber = "010-0000-0000";


    public Victim(UUID contractId, String name, String relationship, String phoneNumber) {
        this.contractId = contractId;
        this.name = name;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
    }

    public void update(String name,String relationship,String phoneNumber){
        this.name = name;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
    }
}