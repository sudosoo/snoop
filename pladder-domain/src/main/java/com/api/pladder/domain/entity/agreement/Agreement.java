package com.api.pladder.domain.entity.agreement;

import com.api.pladder.domain.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement extends BaseEntity {
    private String name;
    private String content;

    public Agreement(String name, String content) {
        this.name = name;
        this.content = content;
    }
}