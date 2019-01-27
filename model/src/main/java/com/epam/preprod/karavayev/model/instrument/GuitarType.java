package com.epam.preprod.karavayev.model.instrument;


public enum GuitarType {
    ACOUSTIC("Acoustic guitar"), ELECTRIC("Electric guitar");

    private String name;


    GuitarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
