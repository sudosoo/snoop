package com.api.pladder.domain.entity.image.enums;


import com.api.pladder.domain.entity.user.enums.UserType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ImageType {
    BUSINESS_REGISTRATION_CERTIFICATE(
            "BU",
            "사업자 등록증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    DETECTIVE_LICENSE(
            "DL",
            "영업 신고증",
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
    TAX_DECLARATION_CERTIFICATE(
            "ID",
            "신분증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    COMPANY_LOGO(
            "MA",
            "회사로고",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    PROFILE(
            "RE",
            "프로필사진",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    );

    public final String prefix;
    public final String description;
    public final List<UserType> createPermissions;
    public final List<UserType> selectPermissions;
    public final List<UserType> deletePermissions;
    public final List<String> extension = Arrays.asList("jpg","jpeg","png","gif"); // 확장자 리스트
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



}
