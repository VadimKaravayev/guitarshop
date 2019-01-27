package com.epam.preprod.karavayev.nonshop.task8.part1and2.util;

import java.util.stream.IntStream;

public class PrimeNumberFinder {

    public boolean isPrime(int number) {
        return number > 1 && IntStream.rangeClosed(2, number / 2)
                .noneMatch(value -> number % value == 0);
    }
}




