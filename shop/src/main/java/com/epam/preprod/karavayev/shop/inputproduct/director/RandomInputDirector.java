package com.epam.preprod.karavayev.shop.inputproduct.director;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.shop.inputproduct.randominput.RandomGuitarInput;
import com.epam.preprod.karavayev.shop.inputproduct.randominput.RandomUkuleleInput;
import com.epam.preprod.karavayev.model.myexception.WrongInstrumentTypeException;

import java.util.HashMap;
import java.util.Map;

public class RandomInputDirector implements InputStrategyDirector {

    @Override
    public ProductInputStrategy getInputStrategy(String product) {
        Map<String, ProductInputStrategy> map = new HashMap<>();
        map.put("guitar", new RandomGuitarInput());
        map.put("ukulele", new RandomUkuleleInput());
        ProductInputStrategy productInputStrategy = map.get(product);
        if (productInputStrategy == null) {
            throw new WrongInstrumentTypeException(product + " not supported");
        }
        return productInputStrategy;
    }
}
