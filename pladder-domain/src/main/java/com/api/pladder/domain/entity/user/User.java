package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.user.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
     @Id
     @UuidGenerator
     @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
     private UUID id;
     private String email;
     private String pwd;
     private UserStatus status;
}
