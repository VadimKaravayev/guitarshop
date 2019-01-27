package com.epam.preprod.karavayev.model.instrument;
import com.epam.preprod.karavayev.model.annotation.Description;

import java.math.BigDecimal;

public class Guitar extends StringInstrument implements Product {

    @Description(value = "guitarType")
    private GuitarType guitarType;

    public Guitar() {
        this.guitarType = GuitarType.ACOUSTIC;
    }

    public Guitar(String name, BigDecimal price) {
        super(name, price);
        this.guitarType = GuitarType.ACOUSTIC;
    }

    public Guitar(int id, String name, BigDecimal price) {
        super(id, name, price);
        guitarType = GuitarType.ACOUSTIC;
    }

    public Guitar(int id, String name, BigDecimal price, GuitarType guitarType) {
        super(id, name, price);
        this.guitarType = guitarType;
    }

    public GuitarType getGuitarType() {
        return guitarType;
    }

    public void setGuitarType(GuitarType guitarType) {
        this.guitarType = guitarType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Guitar guitar = (Guitar) o;

        return guitarType == guitar.guitarType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (guitarType != null ? guitarType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String type = guitarType == null ? "unknown type" : getGuitarType().getName();
        return "* id: " + getId() + " * Guitar * name: " + getName()
               + " * " + type + " * price: " + getPrice();


    }
}