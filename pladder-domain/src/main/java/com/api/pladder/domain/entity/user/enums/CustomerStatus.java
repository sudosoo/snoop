package com.api.pladder.domain.entity.user.enums;

public enum CustomerStatus {
    CERTIFIED("인증"),UNVERIFIED("미인증") ;
        private String status;
        CustomerStatus(String value) {
    }
}
