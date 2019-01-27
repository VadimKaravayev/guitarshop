package com.epam.preprod.karavayev.shop.inputproduct.randominput;

import com.epam.preprod.karavayev.model.instrument.Ukulele;
import com.epam.preprod.karavayev.shop.utils.UserInterface;
import java.util.ResourceBundle;

public class RandomReflexiveUkuleleInput extends RandomReflexiveGuitarInput {

    public RandomReflexiveUkuleleInput(UserInterface console, ResourceBundle resourceBundle) {
        super(console, resourceBundle);
    }

    @Override
    public Ukulele create() {

        Ukulele ukulele = null;
        try {
            ukulele = Ukulele.class.newInstance();
            initAllFields(ukulele);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ukulele;
    }
}
