package com.api.pladder.domain.entity.contract;

import com.api.pladder.domain.entity.contract.enums.Gender;
import com.api.pladder.domain.entity.contract.enums.PersonStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="pd_person_record")
public class PersonRecord {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
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
    private PersonRecord leader;

    @OneToMany(mappedBy = "leader", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PersonRecord> accomplice;

    private PersonStatus status = PersonStatus.UNKNOWN;

    public PersonRecord(UUID contractId, PersonStatus status, String name, Gender gender, int age, String relationship, String workplaceAddr, String impression, String residenceAddr ) {
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
    public void appendAccomplice(PersonRecord accomplice){
        this.leader = this;
        this.accomplice.add(accomplice);
    }

    public void update(String name,
                       Gender gender,
                       int age,
                       String relationship,
                       String workplaceAddr,
                       String impression,
                       String residenceAddr){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.relationship = relationship;
        this.workplaceAddr = workplaceAddr;
        this.impression = impression;
        this.residenceAddr = residenceAddr;
    }

}