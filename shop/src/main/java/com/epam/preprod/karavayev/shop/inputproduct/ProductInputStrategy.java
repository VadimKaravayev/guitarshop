package com.epam.preprod.karavayev.shop.inputproduct;

import com.epam.preprod.karavayev.model.instrument.StringInstrument;

public interface ProductInputStrategy<T extends StringInstrument> {
    T create();
}
