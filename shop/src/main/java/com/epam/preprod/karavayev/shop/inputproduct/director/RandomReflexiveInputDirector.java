package com.epam.preprod.karavayev.shop.inputproduct.director;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;
import com.epam.preprod.karavayev.shop.inputproduct.randominput.RandomReflexiveGuitarInput;
import com.epam.preprod.karavayev.shop.inputproduct.randominput.RandomReflexiveUkuleleInput;
import com.epam.preprod.karavayev.model.myexception.WrongInstrumentTypeException;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class RandomReflexiveInputDirector implements InputStrategyDirector {

    private UserInterface console;
    private ResourceBundle resourceBundle;

    public RandomReflexiveInputDirector(UserInterface console, ResourceBundle resourceBundle) {
        this.console = console;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public ProductInputStrategy getInputStrategy(String product) {
        Map<String, ProductInputStrategy> map = new HashMap<>();
        map.put("guitar", new RandomReflexiveGuitarInput(console, resourceBundle));
        map.put("ukulele", new RandomReflexiveUkuleleInput(console, resourceBundle));
        ProductInputStrategy productInputStrategy = map.get(product);
        if (productInputStrategy == null) {
            throw new WrongInstrumentTypeException(product + " not supported");
        }
        return productInputStrategy;
    }
}
