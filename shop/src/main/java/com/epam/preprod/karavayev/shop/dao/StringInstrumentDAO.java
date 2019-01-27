package com.epam.preprod.karavayev.shop.dao;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;

import java.util.List;

public interface StringInstrumentDAO<T extends StringInstrument> {

    void add(T t);

    List<T> getAll();

    T getById(int id);

    void update(T t);

    void remove(T t);
}
