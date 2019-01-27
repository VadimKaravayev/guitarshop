package com.epam.preprod.karavayev.model.instrument;


import com.epam.preprod.karavayev.model.annotation.Description;

import java.math.BigDecimal;

public class Ukulele extends Guitar {

    @Description(value = "ukuleleType")
    private UkuleleType ukuleleType;

    public Ukulele() {
        super();
    }

    public Ukulele(int id, String name, BigDecimal price, GuitarType guitarType, UkuleleType ukuleleType) {
        super(id, name, price, guitarType);
        this.ukuleleType = ukuleleType;
    }

    public Ukulele(int id, String name, BigDecimal price, UkuleleType ukuleleType) {
        super(id, name, price);
        this.ukuleleType = ukuleleType;
    }

    public Ukulele(int id, String name, BigDecimal price) {
        super(id, name, price, GuitarType.ACOUSTIC);
    }

    public UkuleleType getUkuleleType() {
        return ukuleleType;
    }

    public void setUkuleleType(UkuleleType ukuleleType) {
        this.ukuleleType = ukuleleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ukulele ukulele = (Ukulele) o;

        return ukuleleType != null ? ukuleleType.equals(ukulele.ukuleleType) : ukulele.ukuleleType == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ukuleleType != null ? ukuleleType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "* id: " + getId() + " * Ukulele * name: " + getName() + " * " + getUkuleleType().getName() + " * price: " + getPrice().intValue();
    }
}