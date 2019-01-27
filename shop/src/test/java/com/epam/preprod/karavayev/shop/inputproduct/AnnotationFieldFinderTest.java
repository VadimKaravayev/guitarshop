package com.epam.preprod.karavayev.shop.inputproduct;

import com.epam.preprod.karavayev.model.instrument.Guitar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationFieldFinderTest {

    private AnnotationFieldFinder finder;

    @Before
    public void setUp(){
        finder = new AnnotationFieldFinder();
    }

    @Test
    public void shouldFindAllAnnotatedFieldInAClass() {
        List<Field> allAnnotatedFields = finder.findAllAnnotatedFields(Guitar.class);
        List<String> actual = allAnnotatedFields.stream().map(field -> field.getName()).collect(Collectors.toList());
        List<String> expected = Arrays.asList("name", "price", "guitarType");
        assertTrue(actual.containsAll(expected));
    }
}