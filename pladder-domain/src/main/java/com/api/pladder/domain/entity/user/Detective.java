package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.user.enums.AuthChannel;
import com.api.pladder.domain.entity.user.enums.DetectiveStatus;
import com.api.pladder.domain.entity.user.enums.SpecializeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Detective extends BaseEntity implements User{
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true)
    private String email;
    private String passwd;
    private String phoneNumber;

    private UUID companyId;

    private DetectiveStatus status = DetectiveStatus.UNVERIFIED;

    //TODO :을 기준으로 [0]연차:[1]경력사항 총 년차 계산
    @ElementCollection
    @CollectionTable(name = "detective_career", joinColumns = @JoinColumn(name = "detective_id"))
    @MapKeyColumn(name = "year")
    private Map<Integer,String> career  = null;
    private SpecializeStatus specializeStatus = SpecializeStatus.NONE;
    private AuthChannel authChannel = AuthChannel.LOCAL;

    public Detective(String email, String passwd, String phoneNumber) {
        this.email = email;
        this.passwd = passwd;
        this.phoneNumber = phoneNumber;
    }

    public void updateInfo(String companyId){
        this.phoneNumber = companyId;
    }

    public void setCompany(UUID companyId){
        this.companyId = companyId;
    }

    public int totalCareer() {
        return career.keySet().stream().mapToInt(Integer::intValue).sum();
    }


}
