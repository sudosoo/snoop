package com.api.pladder.domain.entity.company;

import lombok.Getter;

@Getter
public enum ConfirmStatus {
    WAIT_TING("인증대기"), REJECTED("인증거절"),CONFIRMED("인증완료"), ;

    private String status;

    ConfirmStatus(String status) {
        this.status = status;
    }

}
