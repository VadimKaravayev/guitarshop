package com.epam.preprod.karavayev.shop.inputproduct.manualinput;

import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.instrument.UkuleleType;
import com.epam.preprod.karavayev.shop.inputproduct.ReflexiveInstrumentInput;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ResourceBundle;

public abstract class ManualReflexiveInstrumentInput extends ReflexiveInstrumentInput {

    public ManualReflexiveInstrumentInput(UserInterface console, ResourceBundle resourceBundle) {
        super(console, resourceBundle);
    }

    @Override
    public void initFields(StringInstrument instrument, String type, Field field) throws IllegalAccessException {

        switch (type) {
            case "String":
                field.set(instrument, console.takeInput());
                break;
            case "BigDecimal":
                field.set(instrument, new BigDecimal(console.takeInput()));
                break;
            case "GuitarType":
                field.set(instrument, GuitarType.valueOf(console.takeInput().toUpperCase()));
                break;
            case "UkuleleType":
                field.set(instrument, UkuleleType.valueOf(console.takeInput().toUpperCase()));
                break;
        }
    }
}
