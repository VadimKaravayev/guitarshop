package com.epam.preprod.karavayev.nonshop.task8;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.util.PrimeNumberFinder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrimeNumberFinderTest {

    private PrimeNumberFinder finder;

    @Before
    public void setUp() {
        finder = new PrimeNumberFinder();
    }

    @Test
    public void shouldReturnTrueIfNumberIfPrime() {
        int[] expectedResult = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};
        for (int i : expectedResult) {
            assertTrue(finder.isPrime(i));
        }
    }
}