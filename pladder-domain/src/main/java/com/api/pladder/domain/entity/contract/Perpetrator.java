package com.api.pladder.domain.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perpetrator{
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    private String gender;
    private String age;
    private String relationship;
    private String workplace;
    //TODO 파일첨부기능
    private String profile;
    private String impression;
    private String residence;
    private List<UUID> accomplice = new ArrayList<>();
    public void appendAccomplice(UUID accompliceId){
        this.accomplice.add(accompliceId);
    }

}
