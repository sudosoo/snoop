package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import com.api.pladder.domain.entity.user.enums.CustomerStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name="pd_customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity implements User {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID customerId;
    @Column(unique = true)
    private String nickname;
    private String passwd;
    private String phoneNumber;
    //TODO 간편로그인 추후 예정
    //@Enumerated(EnumType.STRING)
    //private AuthChannel authChannel = AuthChannel.LOCAL;
    private CustomerStatus status = CustomerStatus.UNVERIFIED;

     public Customer(String nickname, String passwd) {
        this.passwd = passwd;
        this.nickname = nickname;
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

    public static Customer testEntity(UUID id , String nickname, String passwd){
        return new Customer(id , nickname, passwd);
    }
    private Customer(UUID customerId, String nickname, String passwd) {
        this.customerId = customerId;
        this.nickname = nickname;
        this.passwd = passwd;
    }
}


