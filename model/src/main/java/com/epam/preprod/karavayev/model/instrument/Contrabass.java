package com.epam.preprod.karavayev.model.instrument;

import java.math.BigDecimal;

public class Contrabass extends StringInstrument {
    private static String[] sizeTypes = {"2/4", "3/4", "4/4"};

    private String sizeType;

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        for (String temp: sizeTypes) {
            if (sizeType.equals(temp)) {
                this.sizeType = sizeType;
                return;
            }
        }

        throw new IllegalArgumentException("The size tasktype is wrong");
    }

    public Contrabass() {}

    public Contrabass(int id, String name, BigDecimal price, String sizeType) {
        super(id, name, price);
        this.sizeType = sizeType;
    }

    public Contrabass(int id, String name, BigDecimal price) {
        super(id, name, price);
        setSizeType("4/4");
    }
}
