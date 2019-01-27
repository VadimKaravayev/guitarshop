package com.epam.preprod.karavayev.model.instrument;

import com.epam.preprod.karavayev.model.annotation.Description;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public abstract class StringInstrument implements Serializable {

    private int id;

    @Description(value = "name")
    private String name;

    @Description(value = "price")
    private BigDecimal price;

    public StringInstrument() {
    }

    public StringInstrument(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public StringInstrument(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringInstrument that = (StringInstrument) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StringInstrument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}