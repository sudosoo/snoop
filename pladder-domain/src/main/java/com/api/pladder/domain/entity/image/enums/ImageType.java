package com.api.pladder.domain.entity.image.enums;

import com.api.pladder.core.enums.UserType;
import com.api.pladder.core.utils.enums.EnumUtils;
import com.api.pladder.core.utils.enums.StatusProvider;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ImageType implements StatusProvider {
    BUSINESS_REGISTRATION_CERTIFICATE(
            "BU",
            "사업자 등록증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    DETECTIVE_LICENSE(
            "DL",
            "탐정 면허증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    LOCAL_GOVERNMENT_REPORT(
            "LR",
            "지자체 신고증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    ID_CARD(
            "ID",
            "신분증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    COMPANY_LOGO(
            "CL",
            "회사로고",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    PROFILE(
            "PR",
            "프로필사진",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    EVIDENCE(
            "ED",
                    "증거사진",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
            );

    public final String prefix;
    public final String description;
    public final List<UserType> createPermissions; // 생성
    public final List<UserType> selectPermissions; // 조회
    public final List<UserType> deletePermissions; // 삭제

    ImageType(String prefix, String description, List<UserType> createPermissions, List<UserType> selectPermissions, List<UserType> deletePermissions) {
        this.prefix = prefix;
        this.description = description;
        this.createPermissions = createPermissions; //생성 권한
        this.selectPermissions = selectPermissions; //조회 권한
        this.deletePermissions = deletePermissions; //삭제 권한
    }

    public static ImageType fromPrefix(String reqPrefix) {
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.prefix.equals(reqPrefix))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 prefix에 맞는 ImageType이 없습니다."));
    }

    public static ImageType fromDescription(String reqDescription) {
        return Arrays.stream(ImageType.values())
                .filter(imageType -> imageType.description.equals(reqDescription))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 prefix에 맞는 ImageType이 없습니다."));
    }


    public static ImageType fromStringStatus(String status) {
        return EnumUtils.INSTANCE.fromStringStatus(ImageType.class, status);
    }

    @NotNull
    @Override
    public String getStringStatus() {
        return this.description;
    }
}
