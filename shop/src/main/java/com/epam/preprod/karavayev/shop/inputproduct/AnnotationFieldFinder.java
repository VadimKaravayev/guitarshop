package com.epam.preprod.karavayev.shop.inputproduct;


import com.epam.preprod.karavayev.model.annotation.Description;
import com.epam.preprod.karavayev.model.instrument.StringInstrument;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class AnnotationFieldFinder {

    public List<Field> findAllAnnotatedFields(Class<?> instrument) {
        List<Field> list = new ArrayList<>();
        while (instrument != StringInstrument.class.getSuperclass()) {
            Field[] declaredFields = instrument.getDeclaredFields();
            for (int i = declaredFields.length - 1; i >= 0; i--) {
                list.add(declaredFields[i]);
            }
            instrument = instrument.getSuperclass();
        }
        Iterator<Field> iterator = new LinkedList<>(list).descendingIterator();
        return Lists.newArrayList(iterator).stream().filter(AnnotationFieldFinder::hasDescription).collect(Collectors.toList());
    }

    private static boolean hasDescription(Field field) {
        Description description = field.getAnnotation(Description.class);
        return description != null;
    }
}

