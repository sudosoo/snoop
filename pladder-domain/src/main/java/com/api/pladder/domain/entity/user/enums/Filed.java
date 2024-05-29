package com.api.pladder.domain.entity.user.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public enum Filed {
    NONE("없음"),AFFAIR("불륜/외도"),FRAUD("사기/횡령"),VIOLENCE("폭력/협박"),SEXUAL_HARASSMENT("성희롱/성추행"),MISSING("실종"),
    INDUSTRY("산업스파이"),MEDIATION("중개"),ETC("기타");
    private String status;

    Filed(String value) {
    }
}