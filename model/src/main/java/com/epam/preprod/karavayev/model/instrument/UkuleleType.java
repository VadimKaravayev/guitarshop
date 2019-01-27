package com.epam.preprod.karavayev.model.instrument;

public enum UkuleleType {
    SOPRANO("Ukulele soprano"), CONCERT("Ukulele concert"), TENOR("Ukulele tenor"), BARITONE("Ukulele baritone");

    private String name;

    UkuleleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
