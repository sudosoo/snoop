package com.api.pladder.domain.entity.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractStatus {
    WAITING("신청대기"),APPLY("신청완료"),ONGOING("진행중"),COMPLETED("완료"),CANCELED("취소/환불");
    private String status;
}
