package org.example.entity.enums;

public enum ShoeProductType {
    SPORT("SPORT"),
    CLASSIC("CLASSIC"),
    OLD_FASHION("OLD_FASHION"),
    UNKNOWN("UNKNOWN");


    private final String value;

    ShoeProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
