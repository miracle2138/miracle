package com.jssgwl.data.abroad.constant;

import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * @Author 栗垚
 * @Date 2022/11/30
 */
public enum BuildType {
    Equity(20007, "股权类"), // 股权类
    Infrastructure(20008, "基建类"), // 基建类
    Merger(20009, "开发权并购"), // 开发权并购
    ;
    private final int value;
    private final String name;

    BuildType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Nullable
    public static BuildType fromValue(int value) {
        return Arrays.stream(BuildType.values())
                .filter(e -> e.getValue() == value).findFirst().orElse(null);
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

