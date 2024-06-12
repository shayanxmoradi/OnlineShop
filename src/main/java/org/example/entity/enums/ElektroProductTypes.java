package org.example.entity.enums;

public enum ElektroProductTypes {
        COMPUTER("COMPUTER"),
        RADIO("RADIO"),
        TELEVISION("TELEVISION"),
        PHONE("PHONE"),
    UNKNOWN("UNKNOWN");
    private final String value;

    ElektroProductTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
