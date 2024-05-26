package com.api.pladder.domain.entity.user;

import java.util.UUID;

//유저 Wrapper 클래스
public interface User {
    UUID getId();
    Boolean getIsActive();
}
