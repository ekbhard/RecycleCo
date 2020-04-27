package com.nemytow.recycleCo.RecycleCo.dto;

public enum TrashEnum {

    BIG_BATTERIES("big_batteries"),
    CIRCLE_BATTERIES("circle_batteries"),
    OTHER("other"),
    PHONE_BATTERY("phone_battery"),
    RESOLVED("resolved");

    private String name;

    TrashEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
