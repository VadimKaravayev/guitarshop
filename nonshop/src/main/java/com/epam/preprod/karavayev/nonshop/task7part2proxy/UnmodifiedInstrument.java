package com.epam.preprod.karavayev.nonshop.task7part2proxy;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.Product;

import java.lang.reflect.Proxy;

public class UnmodifiedInstrument {

    public Product create(Product product) {
        Product proxy = (Product) Proxy.newProxyInstance(
                Guitar.class.getClassLoader(),
                Guitar.class.getInterfaces(),
                new MyUnmodifiableHandler(product));
        return proxy;
    }
}
