package com.api.pladder.domain.entity.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    ADMIN("admin", "/api/admin"),
    DETECTIVE("detective", "/api/detective"),
    CUSTOMER("customer", "/api/customer"),
    UNKNOWN("open", "/api/open");

    private final String authorization;
    private final String requestMapper;
    public static UserType fromString(String value) {
        for (UserType userType : UserType.values()) {
            if (userType.name().equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 유저타입 입니다: " + value);
    }
}