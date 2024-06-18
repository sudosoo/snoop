package com.api.pladder.domain.entity.contract.enums;

import com.api.pladder.core.utils.enums.EnumUtils;
import com.api.pladder.core.utils.enums.StatusProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public enum ContractStatus implements StatusProvider {
    /*
    * 신청서 작성 - 신청 중
    * 신청서 승인 - 신청 완료
    * 입금 완료 - 진행중
    * 조사 완료 - 완료
    * 취소 - 취소/환불
    * */
    WAITING("신청중"),APPLY("신청완료"),ONGOING("진행중"),COMPLETED("완료"),CANCELED("취소/환불");
    private String status;

    @NotNull
    @Override
    public String getStringStatus() {
        return this.status;
    }

    public static ContractStatus fromStatus(String status) {
        return EnumUtils.INSTANCE.fromStringStatus(ContractStatus.class, status);
    }

}
