package com.epam.preprod.karavayev.nonshop.task7part2proxy;

import com.epam.preprod.karavayev.model.instrument.Product;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class BasedOnMapInstrument implements ProductFactory {
    @Override
    public Product getNewInstrument() {
        InvocationHandler handler = new MyMapHandler();
        Class[] classes = new Class[]{Product.class};
        Object proxy = Proxy.newProxyInstance(Product.class.getClassLoader(), classes, handler);
        return (Product) proxy;
    }
}
