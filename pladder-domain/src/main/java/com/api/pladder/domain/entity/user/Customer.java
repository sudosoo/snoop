package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.user.enums.CustomerStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Entity(name="pd_customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity implements User {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID customerId;
    @Column(unique = true)
    private String nickName;
    private String passwd;
    private String phoneNumber;
    //TODO 간편로그인 추후 예정
    //@Enumerated(EnumType.STRING)
    //private AuthChannel authChannel = AuthChannel.LOCAL;
    private CustomerStatus status = CustomerStatus.UNVERIFIED;

    private Customer(String nickName, String passwd) {
        this.passwd = passwd;
        this.nickName = nickName;
    }

    public void updateInfo(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void updatePasswd(String pwd) {
        this.passwd = pwd;
    }

    //계약서 작성하면 인증상태로 변경
    public void updateCertified() {
        this.status = CustomerStatus.CERTIFIED;
    }

    public static Customer testEntity(UUID id , String nickName, String passwd){
        return new Customer(id , nickName, passwd);
    }
    private Customer(UUID customerId, String nickName, String passwd) {
        this.customerId = customerId;
        this.nickName = nickName;
        this.passwd = passwd;
    }

    public Customer createTestEntity(UUID id, String nickName, String passwd){
        return new Customer(id,nickName,passwd);
    }
}


