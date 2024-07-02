package com.api.pladder.domain.entity.contract;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.contract.enums.Gender;
import com.api.pladder.domain.entity.contract.enums.PersonStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "pd_person")
@Table(indexes = {@Index(name = "idx_person_id_status", columnList = "id, status")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person extends BaseEntity {

    private UUID contractId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNKNOWN;
    private int age = 0;
    private String relationship;
    private String workplaceAddr;
    //TODO 파일첨부기능
    private String profileId;
    //인상착의
    private String impression;
    //집 주소
    private String residenceAddr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Person leader;

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private List<Person> accomplice = new ArrayList<>();

    private PersonStatus status = PersonStatus.UNKNOWN;

    public Person(UUID contractId, PersonStatus status, String name, Gender gender, int age, String relationship, String workplaceAddr, String impression, String residenceAddr ) {
        this.contractId = contractId;
        this.status = status;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.relationship = relationship;
        this.workplaceAddr = workplaceAddr;
        this.impression = impression;
        this.residenceAddr = residenceAddr;
    }

    public void appendAccomplice(Person accomplice){
        this.leader = this;
        this.accomplice.add(accomplice);
    }

}