package com.epam.preprod.karavayev.model.instrument;
import java.math.BigDecimal;

public interface Product {

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    GuitarType getGuitarType();

    void setGuitarType(GuitarType guitarType);
}
