package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.user.enums.AuthChannel;
import com.api.pladder.domain.entity.user.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity(name ="pd_customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity implements User {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true)
    private String email;
    private String passwd;
    private String nickName;
    //TODO  계약서
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private AuthChannel authChannel = AuthChannel.LOCAL;
    private CustomerStatus status = CustomerStatus.UNVERIFIED;
    public Customer(String email, String passwd, String phoneNumber, String nickName) {
        this.email = email;
        this.passwd = passwd;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
    }

    public Customer(String email, String passwd, String phoneNumber, String nickName, AuthChannel authChannel) {
        this.email = email;
        this.passwd = passwd;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.authChannel = authChannel;
    }

    public void updateInfo(String nickName, String phoneNumber) {
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public void updatePasswd(String pwd) {
        this.passwd = pwd;
    }

    //계약서 작성하면 인증상태로 변경
    public void updateCertified() {
        this.status = CustomerStatus.CERTIFIED;
    }
}
