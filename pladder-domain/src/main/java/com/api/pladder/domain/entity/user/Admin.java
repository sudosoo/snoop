package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Entity(name="pd_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity implements User{
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false,columnDefinition = "BINARY(16)")
    private UUID adminId;
    @Column(unique = true)
    private String email;
    private String pwd;

}
