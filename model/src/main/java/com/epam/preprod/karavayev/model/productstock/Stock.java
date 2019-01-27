package com.epam.preprod.karavayev.model.productstock;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.instrument.Ukulele;
import com.epam.preprod.karavayev.model.instrument.UkuleleType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stock {

    private Map<Integer, StringInstrument> productsInStock;

    public Stock() {
        productsInStock = new HashMap<>();
        productsInStock.put(1, new Guitar(1, "Yamaha", new BigDecimal(1200), GuitarType.ACOUSTIC));
        productsInStock.put(2, new Guitar(2,"Ibanez", new BigDecimal(1300), GuitarType.ELECTRIC));
        productsInStock.put(3, new Guitar(3,"Taylor", new BigDecimal(1400), GuitarType.ACOUSTIC));
        productsInStock.put(4, new Ukulele(4, "Kala", new BigDecimal(500), GuitarType.ACOUSTIC, UkuleleType.BARITONE));
        productsInStock.put(5, new Ukulele(5,"Lanikai", new BigDecimal(600), GuitarType.ACOUSTIC, UkuleleType.CONCERT));
        productsInStock.put(6, new Ukulele(6,"Mahalo", new BigDecimal(700), GuitarType.ACOUSTIC, UkuleleType.SOPRANO));
    }

    public Stock(List<StringInstrument> instruments) {
        productsInStock = instruments
                .stream()
                .collect(Collectors.toMap(StringInstrument::getId, product-> product));
    }

    public Map<Integer, StringInstrument> getProductsInStock() {
        return Collections.unmodifiableMap(productsInStock);
    }

    public StringInstrument getProductById(int id) {
        if (!productsInStock.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return productsInStock.get(id);
    }

    public int getProductCount() {
        return productsInStock.size();
    }

    public boolean addProductToStock(StringInstrument instrument) {
        if (instrument == null) {
            return false;
        }
        if (instrument.getId() <= 0) {
            int id = productsInStock.isEmpty() ? 1 : Collections.max(productsInStock.keySet()) + 1;
            instrument.setId(id);
        }
        if (!productsInStock.containsKey(instrument.getId())) {
            productsInStock.put(instrument.getId(), instrument);
            return true;
        }
        return false;
    }
}
