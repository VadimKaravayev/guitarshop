package com.epam.preprod.karavayev.shop.inputproduct;

import com.epam.preprod.karavayev.model.annotation.Description;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.epam.preprod.karavayev.shop.utils.UserInterface;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ResourceBundle;

public abstract class ReflexiveInstrumentInput  implements ProductInputStrategy<StringInstrument> {

    protected UserInterface console;
    protected ResourceBundle resourceBundle;
    protected AnnotationFieldFinder fieldFinder;

    public ReflexiveInstrumentInput(UserInterface console, ResourceBundle resourceBundle) {
        this.console = console;
        this.resourceBundle = resourceBundle;
        fieldFinder = new AnnotationFieldFinder();
    }

    public abstract void initFields(StringInstrument instrument, String type, Field field) throws IllegalAccessException;

    public void initAllFields(StringInstrument instrument) throws IllegalAccessException {
        List<Field> allAnnotatedFields = fieldFinder.findAllAnnotatedFields(instrument.getClass());
        if (!allAnnotatedFields.isEmpty()) {
            for (Field field : allAnnotatedFields) {
                field.setAccessible(true);
                console.promptMessage(resourceBundle.getString(field.getAnnotation(Description.class).value()));
                String typeName = field.getType().getSimpleName();
                initFields(instrument, typeName, field);
            }
        }
    }
}
