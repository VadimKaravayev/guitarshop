package com.epam.preprod.karavayev.db;

public enum Join {
    INNER("INNER"), RIGHT("RIGHT"), LEFT("LEFT"), OUTER("OUTER");

    private String type;

    Join(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
