package com.epam.preprod.karavayev.shop.dao;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.productstock.Stock;

import java.util.ArrayList;
import java.util.List;

public class StringInstrumentService implements StringInstrumentDAO<StringInstrument> {

    private Stock stock;

    public StringInstrumentService(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void add(StringInstrument instrument) {

    }

    @Override
    public List<StringInstrument> getAll() {
        List<StringInstrument> allProducts = new ArrayList<>(stock.getProductsInStock().values());
        return allProducts;
    }

    @Override
    public StringInstrument getById(int id) {
        return stock.getProductById(id);
    }

    @Override
    public void update(StringInstrument instrument) {

    }

    @Override
    public void remove(StringInstrument instrument) {

    }
}
