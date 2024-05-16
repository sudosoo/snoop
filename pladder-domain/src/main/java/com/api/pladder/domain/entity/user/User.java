package com.api.pladder.domain.entity.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
     private String id;
     private String email;
     private String pwd;
     private UserStatus status;
}
