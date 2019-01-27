package com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.director.TaskDirector;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.type.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorPrimeNumberCollector implements PrimesCollectable {

    private List<Integer> primes;
    private TaskType taskType;
    private ExecutorService executor;

    public ExecutorPrimeNumberCollector(TaskType taskType) {
        this.primes = new ArrayList<>();
        this.taskType = taskType;
    }

    @Override
    public List<Integer> collectPrimes(int a, int z, int numberOfThreads) {
        List<ArrayList<Integer>> shares = allocateNumbers(a, z, numberOfThreads);
        executor = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            List<Integer> share = shares.get(i);
            executor.execute(new TaskDirector(share, primes).getTask(taskType));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            try {
                executor.awaitTermination(1, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        return primes;
    }
}
