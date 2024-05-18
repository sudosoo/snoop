package com.api.pladder.domain.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity(name ="pd_customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends User{
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String email;
    private String passwd;
    private String phoneNumber;
    private String nickName;
    @Enumerated(EnumType.STRING)
    private AuthChannel authChannel = AuthChannel.LOCAL;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

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
}
