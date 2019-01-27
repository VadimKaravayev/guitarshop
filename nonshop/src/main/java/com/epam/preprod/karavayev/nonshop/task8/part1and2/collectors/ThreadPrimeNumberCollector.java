package com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.director.TaskDirector;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.type.TaskType;

import java.util.ArrayList;
import java.util.List;

public class ThreadPrimeNumberCollector implements PrimesCollectable {

    private List<Integer> primes;
    private Thread[] threads;
    private TaskType taskType;

    public ThreadPrimeNumberCollector (TaskType taskType) {
        this.primes = new ArrayList<>();
        this.taskType = taskType;
    }

    @Override
    public List<Integer> collectPrimes(int a, int z, int numberOfThreads) {
        List<ArrayList<Integer>> shares = allocateNumbers(a, z, numberOfThreads);

        threads = new Thread[numberOfThreads];

        for (int i = 0; i < threads.length; i++) {
            ArrayList<Integer> share = shares.get(i);
            threads[i] = new Thread(new TaskDirector(share, primes).getTask(taskType));
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        return primes;
    }
}
