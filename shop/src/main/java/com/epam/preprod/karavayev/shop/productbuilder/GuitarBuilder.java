package com.epam.preprod.karavayev.shop.productbuilder;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;

import java.math.BigDecimal;

public class GuitarBuilder  {

    private int id = -1;
    private String name;
    private BigDecimal price;
    private GuitarType guitarType;

    public GuitarBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public GuitarBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public GuitarBuilder buildGuitarType(GuitarType guitarType) {
        this.guitarType = guitarType;
        return this;
    }

    private Guitar newGuitar() {
        Guitar guitar = new Guitar();
        guitar.setId(id);
        guitar.setName(name);
        guitar.setPrice(price);
        guitar.setGuitarType(guitarType);
        return guitar;
    }

    public Guitar build() {
        return newGuitar();
    }
}
