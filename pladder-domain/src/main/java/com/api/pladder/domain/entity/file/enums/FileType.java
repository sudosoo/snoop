package com.api.pladder.domain.entity.file.enums;

import com.api.pladder.core.enums.UserType;
import com.api.pladder.core.exception.AccessDeniedException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum FileType{
    BUSINESS_REGISTRATION_CERTIFICATE(
            "BU",
            "사업자 등록증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    DETECTIVE_LICENSE(
            "AC",
            "아카데미 수료증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    LOCAL_GOVERNMENT_REPORT(
            "HI",
            "건강보험 자격득실 확인서",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    ID_CARD(
            "DE",
            "탐정 자격증",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    PROFILE(
            "PR",
            "프로필사진",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
    ),
    EVIDENCE_IMAGE(
            "EI",
            "증거사진",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
            ),
    EVIDENCE_AUDIO(
            "EO",
            "증거오디오",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
            ),
    SIGN(
            "SI",
                    "서명",
            Arrays.asList(UserType.DETECTIVE),
            Arrays.asList(UserType.CUSTOMER, UserType.DETECTIVE, UserType.ADMIN),
            Arrays.asList(UserType.DETECTIVE, UserType.ADMIN)
            );
    ;

    public final String prefix;
    public final String description;
    public final List<UserType> createPermissions; // 생성
    public final List<UserType> selectPermissions; // 조회
    public final List<UserType> deletePermissions; // 삭제

    FileType(String prefix, String description, List<UserType> createPermissions, List<UserType> selectPermissions, List<UserType> deletePermissions) {
        this.prefix = prefix;
        this.description = description;
        this.createPermissions = createPermissions; //생성 권한
        this.selectPermissions = selectPermissions; //조회 권한
        this.deletePermissions = deletePermissions; //삭제 권한
    }

    public static FileType fromPrefix(String reqPrefix) {
        return Arrays.stream(FileType.values())
                .filter(fileType -> fileType.prefix.equals(reqPrefix))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 prefix에 맞는 ImageType이 없습니다."));
    }

    public void checkSelectPermission(UserType userType) throws AccessDeniedException {
        if (!selectPermissions.contains(userType)) {
            throw new AccessDeniedException("해당 파일을 조회할 권한이 없습니다.");
        }
    }

}
