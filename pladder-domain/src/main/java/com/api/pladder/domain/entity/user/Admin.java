package com.api.pladder.domain.entity.user;

import com.api.pladder.domain.entity.base.BaseEntity;
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
@EqualsAndHashCode(callSuper = false)
@Entity(name="pd_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity implements User{
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID adminId;
    @Column(unique = true)
    private String email;
    private String pwd;

}
