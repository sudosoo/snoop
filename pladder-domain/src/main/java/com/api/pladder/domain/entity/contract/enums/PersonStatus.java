package com.api.pladder.domain.entity.contract.enums;

import com.api.pladder.core.utils.enums.EnumUtils;
import com.api.pladder.core.utils.enums.StatusProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public enum PersonStatus implements StatusProvider {
    PERPETRATOR("가해자"),
    VICTIM("피해자"),
    WITNESS("증인"),
    UNKNOWN("미상");

    private final String status;

    public static ContractStatus fromStatus(String status) {
        return EnumUtils.INSTANCE.fromStringStatus(ContractStatus.class, status);
    }
    @NotNull
    @Override
    public String getStringStatus() {
        return this.status;
    }

}
