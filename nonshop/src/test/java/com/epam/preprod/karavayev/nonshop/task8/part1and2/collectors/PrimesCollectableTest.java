package com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimesCollectableTest {

    private class TestCollector implements PrimesCollectable {

        @Override
        public List<Integer> collectPrimes(int a, int z, int numberOfThreads) {
            return new ArrayList<>();
        }
    }


    @Test
    public void shouldReturnListOfListsWithSpecificallyAllocatedNumbers() {
        PrimesCollectable collector = new TestCollector();
        List<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(1, 6, 11, 16)));
        expected.add(new ArrayList<>(Arrays.asList(2, 7, 12, 17)));
        expected.add(new ArrayList<>(Arrays.asList(3, 8, 13, 18)));
        expected.add(new ArrayList<>(Arrays.asList(4, 9, 14, 19)));
        expected.add(new ArrayList<>(Arrays.asList(5, 10, 15, 20)));

        List<ArrayList<Integer>> actual = collector.allocateNumbers(1, 20, 5);
        assertEquals(actual, expected);

    }
}