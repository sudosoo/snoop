package com.api.pladder.domain.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="pd_perpetrator")
public class Perpetrator{
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    private UUID contractId;
    private String name;
    private String gender;
    private String age;
    private String relationship;
    private String workplaceAddr;
    //TODO 파일첨부기능
    private String profile;
    //인상착의
    private String impression;
    private String residenceAddr;
    //공범 가해자 entityId
    private UUID accompliceId;
}