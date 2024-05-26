package com.api.pladder.domain.entity.user.enums;

public enum DetectiveLevel {
     CERTIFIED("인증"),UNVERIFIED("미인증"),
     ;
     private String status;


     DetectiveLevel(String status) {

     }
}
