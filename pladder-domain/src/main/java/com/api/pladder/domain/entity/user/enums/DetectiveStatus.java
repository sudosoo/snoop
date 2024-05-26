package com.api.pladder.domain.entity.user.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DetectiveStatus {
     CERTIFIED("인증"),UNVERIFIED("미인증") ;

     private String status;

}
