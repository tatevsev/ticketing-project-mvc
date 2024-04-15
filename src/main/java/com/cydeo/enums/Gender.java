package com.cydeo.enums;

public enum Gender {
    MALE("Male"),FEMALE("Female");//we assign value to each ENUM in order to use them in different format when user access them
    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
