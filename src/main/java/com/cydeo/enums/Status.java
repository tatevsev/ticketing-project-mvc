package com.cydeo.enums;

public enum Status {
    OPEN("Open"), IN_PROGRESS("In progress"), COMPLETED("Completed");

    private final String value;

    public String getValue() {
        return value;
    }


    Status(String value) {
        this.value = value;
    }


}


