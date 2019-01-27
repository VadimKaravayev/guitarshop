package com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public interface PrimesCollectable {

    List<Integer> collectPrimes(int startRange, int endRange, int numberOfThreads);

    default List<ArrayList<Integer>> allocateNumbers(int startRange, int endRange, int numberOfThreads) {

        List<ArrayList<Integer>> results = new ArrayList<>();
        IntStream.range(0, numberOfThreads).forEach(value -> results.add(new ArrayList<>()));
        int[] ints = IntStream.rangeClosed(startRange, endRange).toArray();

        for (int i = 0; i < results.size(); i++) {
            for (int y = i; y < ints.length; y += numberOfThreads) {
                results.get(i).add(ints[y]);
            }
        }
        return results;
    }
}
