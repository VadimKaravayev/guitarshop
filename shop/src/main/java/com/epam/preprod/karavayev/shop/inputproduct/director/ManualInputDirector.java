package com.epam.preprod.karavayev.shop.inputproduct.director;

import com.epam.preprod.karavayev.shop.inputproduct.manualinput.ManualGuitarInput;
import com.epam.preprod.karavayev.shop.inputproduct.manualinput.ManualUkuleleInput;
import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.model.myexception.WrongInstrumentTypeException;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.util.HashMap;
import java.util.Map;

public class ManualInputDirector implements InputStrategyDirector {

    UserInterface console;

    public ManualInputDirector(UserInterface console) {
        this.console = console;
    }

    @Override
    public ProductInputStrategy getInputStrategy(String product) {
        Map<String, ProductInputStrategy> map = new HashMap<>();
        map.put("guitar", new ManualGuitarInput(console));
        map.put("ukulele", new ManualUkuleleInput(console));
        ProductInputStrategy productInputStrategy = map.get(product);
        if (productInputStrategy == null) {
            throw new WrongInstrumentTypeException(product + " not supported");
        }
        return productInputStrategy;
    }
}
