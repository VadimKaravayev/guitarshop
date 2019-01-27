package com.epam.preprod.karavayev.shop.productbuilder;

import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.Ukulele;
import com.epam.preprod.karavayev.model.instrument.UkuleleType;

import java.math.BigDecimal;

public class UkuleleBuilder {

    private int id = -1;
    private String name;
    private BigDecimal price;
    private GuitarType guitarType;
    private UkuleleType ukuleleType;

    public UkuleleBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public UkuleleBuilder buildPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UkuleleBuilder buildGuitarType(GuitarType guitarType) {
        this.guitarType = guitarType;
        return this;
    }

    public UkuleleBuilder buildUkuleleType(UkuleleType ukuleleType) {
        this.ukuleleType = ukuleleType;
        return this;
    }

    private Ukulele newUkulele() {
        Ukulele ukulele = new Ukulele();
        ukulele.setId(id);
        ukulele.setName(name);
        ukulele.setPrice(price);
        ukulele.setGuitarType(guitarType);
        ukulele.setUkuleleType(ukuleleType);
        return ukulele;
    }

    public Ukulele build() {
        return newUkulele();
    }
}
