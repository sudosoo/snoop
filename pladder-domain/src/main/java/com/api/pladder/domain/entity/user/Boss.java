package com.api.pladder.domain.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Boss extends User{

    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String email;
    private String passwd;
    private String phoneNumber;
    private String companyId;
    private UserStatus status = UserStatus.ACTIVE;
    private AuthChannel authChannel = AuthChannel.LOCAL;

    public Boss(String email, String passwd, String phoneNumber) {
        this.email = email;
        this.passwd = passwd;
        this.phoneNumber = phoneNumber;
    }

    public void updateInfo(String companyId){
        this.phoneNumber = companyId;
    }
    public void setCompany(String companyId){
        this.companyId = companyId;
    }

}
