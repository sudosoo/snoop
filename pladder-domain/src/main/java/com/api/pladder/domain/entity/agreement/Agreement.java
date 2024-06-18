package com.api.pladder.domain.entity.agreement;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID agreementId;
    private String name;
    private String content;

    public Agreement(String name, String content) {
        this.name = name;
        this.content = content;
    }
}