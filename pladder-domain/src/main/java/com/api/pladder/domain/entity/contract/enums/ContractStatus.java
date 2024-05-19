package com.api.pladder.domain.entity.contract.enums;

import lombok.Getter;

@Getter
public enum ContractStatus {
    APPLY("신청완료"),WAITING("신청대기"),ONGOING("진행중"),COMPLETED("완료"),CANCELED("취소/환불");
    private String status;
    ContractStatus(String status) {
        this.status = status;
    }

}
