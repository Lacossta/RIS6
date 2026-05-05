package com.anysound.model;

public enum TransportType {
    BUS("Автобус"), TRAIN("Поезд"), AIRPLANE("Самолёт");

    private final String displayName;
    TransportType(String displayName) { this.displayName = displayName; }
    public String getDisplayName() { return displayName; }
}