package com.api.pladder.domain.entity.company.enums;

import com.api.pladder.core.utils.enums.EnumUtils;
import com.api.pladder.core.utils.enums.StatusProvider;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum ConfirmStatus implements StatusProvider {
    WAITING("인증대기"), REJECTED("인증거절"),CONFIRMED("인증완료") ;

    public abstract ConfirmStatus confirmAction();
    private String status;
    private
    ConfirmStatus(String status) {
        this.status = status;
    }

    @NotNull
    @Override
    public String getStringStatus() {
        return this.status;
    }

    public static ConfirmStatus fromStringStatus(String status) {
        return EnumUtils.INSTANCE.fromStringStatus(ConfirmStatus.class, status);
    }

}
