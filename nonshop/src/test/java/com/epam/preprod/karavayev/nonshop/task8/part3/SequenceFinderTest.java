package com.epam.preprod.karavayev.nonshop.task8.part3;

import static org.junit.Assert.*;

import com.epam.preprod.karavayev.nonshop.task8.part3.util.SequenceFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SequenceFinderTest {

    private SequenceFinder finder;

    @Before
    public void setUp() {
        finder = new SequenceFinder();
    }

    @Test
    public void shouldReturnFirstAndSecondOccurrencesOfBytes() {
        byte[] bytes1 = "My name is Johnny! Hello, ladies! Hello, gentlemen!".getBytes();
        byte[] bytes2 = "Hello".getBytes();
        int[] indexes = finder.getIndexesOfOccurrences(bytes1, bytes2);
        int[] expected = {19, 34};
        assertEquals(Arrays.toString(indexes), Arrays.toString(expected));
    }

}