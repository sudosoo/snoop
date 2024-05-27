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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity implements User{

    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID id;
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
}
