package com.api.pladder.domain.entity.contract;

import com.api.pladder.domain.entity.contract.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="pd_perpetrator")
public class Perpetrator {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID perpetratorId;
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
    private Perpetrator leader;

    @OneToMany(mappedBy = "leader", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Perpetrator> accomplice;

    public Perpetrator(UUID contractId, String name, Gender gender, int age, String relationship, String workplaceAddr, String impression, String residenceAddr) {
        this.contractId = contractId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.relationship = relationship;
        this.workplaceAddr = workplaceAddr;
        this.impression = impression;
        this.residenceAddr = residenceAddr;
    }
    public void appendAccomplice(Perpetrator accomplice){
        this.accomplice.add(accomplice);
        accomplice.appendLeader(this);
    }
    public void appendLeader(Perpetrator leader){
        this.leader = leader;
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