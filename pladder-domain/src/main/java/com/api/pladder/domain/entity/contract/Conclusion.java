package com.api.pladder.domain.entity.contract;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="pd_conclusion")
public class Conclusion{
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID conclusionId;
    private String description;
    private String file;
}