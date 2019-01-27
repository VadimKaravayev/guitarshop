package com.epam.preprod.karavayev.nonshop.task8.part1and2.task;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.util.PrimeNumberFinder;

import java.util.List;

public class CommonCollectionTask implements Runnable {

    private PrimeNumberFinder finder;
    private List<Integer> primes;
    private List<Integer> share;

    public CommonCollectionTask(List<Integer> share, List<Integer> primes) {
        this.share = share;
        this.primes = primes;
        finder = new PrimeNumberFinder();
    }

    @Override
    public void run() {
        for (int number: share) {
            synchronized (primes) {
                if (finder.isPrime(number)) {
                    primes.add(number);
                }
            }
        }
    }
}
