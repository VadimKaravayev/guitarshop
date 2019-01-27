package com.epam.preprod.karavayev.nonshop.task8.part1and2.task;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.util.PrimeNumberFinder;

import java.util.ArrayList;
import java.util.List;

public class IndividualCollectionTask implements Runnable {

    private List<Integer> share;
    private PrimeNumberFinder finder;
    private List<Integer> primes;

    public IndividualCollectionTask(List<Integer> share, List<Integer> primes) {
        this.share = share;
        finder = new PrimeNumberFinder();
        this.primes = primes;
    }

    @Override
    public void run() {
        List<Integer> localList = new ArrayList<>();
        for (int number: share) {
            synchronized (primes) {
                if (finder.isPrime(number)) {
                    localList.add(number);
                }
            }
        }
        primes.addAll(localList);
    }
}
