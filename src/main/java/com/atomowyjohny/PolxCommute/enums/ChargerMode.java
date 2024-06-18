package com.atomowyjohny.PolxCommute.enums;

public enum ChargerMode {
    CHARGING(0),
    DISCHARGING(1),
    STORAGE(2);

    private final int value;

    ChargerMode(int value) {
        this.value = value;
    }

    public static ChargerMode fromInt(int value) {
        return switch (value) {
            case 0 -> ChargerMode.CHARGING;
            case 1 -> ChargerMode.DISCHARGING;
            case 2 -> ChargerMode.STORAGE;
            default -> null;
        };
    }

    public int getValue() {
        return value;
    }
}
