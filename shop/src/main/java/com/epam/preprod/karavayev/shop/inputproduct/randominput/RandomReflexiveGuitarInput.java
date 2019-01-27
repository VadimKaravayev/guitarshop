package com.epam.preprod.karavayev.shop.inputproduct.randominput;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.util.ResourceBundle;

public class RandomReflexiveGuitarInput extends RandomReflexiveInstrumentInput {

    public RandomReflexiveGuitarInput(UserInterface console, ResourceBundle resourceBundle) {
        super(console, resourceBundle);
    }

    @Override
    public Guitar create() {
        Guitar guitar = null;
        try {
            guitar = Guitar.class.newInstance();
            initAllFields(guitar);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return guitar;
    }
}
