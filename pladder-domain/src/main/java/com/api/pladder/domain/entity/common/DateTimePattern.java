package com.api.pladder.domain.entity.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum DateTimePattern {
    STANDARD("yyyy-MM-dd HH:mm:ss"),
    COMPACT("yyyy-MM-dd HH:mm");

    public static final String STANDARD_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String COMPACT_PATTERN = "yyyy-MM-dd HH:mm";

    private String pattern;

}

