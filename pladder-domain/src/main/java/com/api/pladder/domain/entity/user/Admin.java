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
public class Admin {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true)
    private String email;
    private String pwd;

    private Admin(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
