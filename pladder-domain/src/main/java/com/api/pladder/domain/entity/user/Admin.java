package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Entity(name="pd_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity implements User{
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID adminId;
    @Column(unique = true)
    private String email;
    private String pwd;

    private Admin(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public Boolean isActive() {
        return true;
    }
    @Override
    public void withdrawn() {
        throw new UnsupportedOperationException("관리자는 탈퇴할 수 없습니다.");
    }
}
