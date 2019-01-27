package com.epam.preprod.karavayev.shop.inputproduct.randominput;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.Ukulele;
import com.epam.preprod.karavayev.model.instrument.UkuleleType;
import com.epam.preprod.karavayev.shop.productbuilder.UkuleleBuilder;

import java.math.BigDecimal;
import java.util.Random;

public class RandomUkuleleInput implements ProductInputStrategy<Ukulele> {

    private String name;
    private BigDecimal price;
    private GuitarType guitarType;
    private UkuleleType ukuleleType;

    @Override
    public Ukulele create() {
        Random random = new Random();
        name = "name " + random.nextInt();
        price = new BigDecimal(random.ints(50, 2001)
                .findFirst().getAsInt());
        GuitarType[] values1 = GuitarType.values();
        guitarType = values1[random.nextInt(values1.length)];
        UkuleleType[] values2 = UkuleleType.values();
        ukuleleType = values2[random.nextInt(values2.length)];
        return new UkuleleBuilder().buildName(name).buildPrice(price)
                .buildGuitarType(guitarType).buildUkuleleType(ukuleleType).build();

    }
}
