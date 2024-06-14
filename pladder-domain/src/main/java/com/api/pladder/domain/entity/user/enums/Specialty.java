package com.api.pladder.domain.entity.user.enums;

import com.api.pladder.core.utils.enums.EnumUtils;
import com.api.pladder.core.utils.enums.StatusProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum Specialty implements StatusProvider {
    NONE("없음"),
    AFFAIR("불륜/외도"),
    FRAUD("사기/횡령"),
    VIOLENCE("폭력/협박"),
    SEXUAL_HARASSMENT("성희롱/성추행"),
    MISSING("실종"),
    INDUSTRY("산업스파이"),
    MEDIATION("중개"),
    ETC("기타");

    private String status;

    Specialty(String status) {
        this.status = status;
    }

    @NotNull
    @Override
    public String getStringStatus() {
        return this.status;
    }

    public static Specialty fromStatus(String status) {
        return EnumUtils.INSTANCE.fromStringStatus(Specialty.class, status);
    }
}
