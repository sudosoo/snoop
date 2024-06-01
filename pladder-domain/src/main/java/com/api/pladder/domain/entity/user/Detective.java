package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.user.enums.AuthChannel;
import com.api.pladder.domain.entity.user.enums.DetectiveStatus;
import com.api.pladder.domain.entity.user.enums.Filed;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Map;
import java.util.UUID;

@Getter
@Entity(name="pd_detective")
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
    @MapKeyColumn(name = "career_year")
    private Map<Integer,String> career  = null;
    private Filed filed = Filed.NONE;
    //TODO 간편로그인 추후 예정
    //private AuthChannel authChannel = AuthChannel.LOCAL;
    private boolean isActive = true;
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
    public Boolean isActive() { return this.isActive; }

}
