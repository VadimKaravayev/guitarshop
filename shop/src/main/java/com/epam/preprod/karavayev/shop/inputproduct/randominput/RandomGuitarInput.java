package com.epam.preprod.karavayev.shop.inputproduct.randominput;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.shop.productbuilder.GuitarBuilder;

import java.math.BigDecimal;
import java.util.Random;

public class RandomGuitarInput implements ProductInputStrategy<Guitar> {

    private String name;
    private BigDecimal price;
    private GuitarType guitarType;

    @Override
    public Guitar create() {
        Random random = new Random();
        name = "name " + random.nextInt();
        price = new BigDecimal(random.ints(50, 2001)
                .findFirst().getAsInt());
        GuitarType[] values = GuitarType.values();
        guitarType = values[random.nextInt(values.length)];
        return new GuitarBuilder().buildName(name).buildPrice(price).buildGuitarType(guitarType).build();
    }
}
