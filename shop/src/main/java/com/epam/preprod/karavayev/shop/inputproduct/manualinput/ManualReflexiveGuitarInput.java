package com.epam.preprod.karavayev.shop.inputproduct.manualinput;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.util.ResourceBundle;

public class ManualReflexiveGuitarInput extends ManualReflexiveInstrumentInput {

    public ManualReflexiveGuitarInput(UserInterface console, ResourceBundle resourceBundle) {
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
