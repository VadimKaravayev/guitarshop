package com.epam.preprod.karavayev.shop.inputproduct.randominput;

import com.epam.preprod.karavayev.model.instrument.GuitarType;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.model.instrument.UkuleleType;
import com.epam.preprod.karavayev.shop.inputproduct.ReflexiveInstrumentInput;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Random;
import java.util.ResourceBundle;


public abstract class RandomReflexiveInstrumentInput extends ReflexiveInstrumentInput {

    public RandomReflexiveInstrumentInput(UserInterface console, ResourceBundle resourceBundle) {
        super(console, resourceBundle);
    }

    @Override
    public void initFields(StringInstrument instrument, String type, Field field) throws IllegalAccessException {
        Random random = new Random();
        switch (type) {
            case "String":
                field.set(instrument, "name" + random.nextInt());
                break;
            case "BigDecimal":
                field.set(instrument, new BigDecimal(random.ints(50, 2001)
                        .findFirst().getAsInt()));
                break;
            case "GuitarType":
                GuitarType[] guitarTypes = GuitarType.values();
                field.set(instrument, guitarTypes[random.nextInt(guitarTypes.length)]);
                break;
            case "UkuleleType":
                UkuleleType[] ukuleleTypes = UkuleleType.values();
                field.set(instrument, ukuleleTypes[random.nextInt(ukuleleTypes.length)]);
        }
    }
}
