package com.api.pladder.domain.entity.contract.enums;

import com.api.pladder.core.utils.enums.EnumUtils;
import com.api.pladder.core.utils.enums.StatusProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public enum Gender implements StatusProvider {
    FEMALE("여성"),MALE("남성"),UNKNOWN("미상");

    private final String status;
    @NotNull
    @Override
    public String getStringStatus() {
        return this.status;
    }

    public static ContractStatus fromStatus(String status) {
        return EnumUtils.INSTANCE.fromStringStatus(ContractStatus.class, status);
    }
}
