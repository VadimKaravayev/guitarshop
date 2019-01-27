package com.epam.preprod.karavayev.nonshop.task8;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.ExecutorPrimeNumberCollector;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.PrimesCollectable;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.type.TaskType;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.ThreadPrimeNumberCollector;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionDumperTest {

    private PrimesCollectable collector;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnListOfPrimeNumbersFoundInSpecifiedRangeBySpecifiedNumberOfThreads() {
        collector = new ThreadPrimeNumberCollector(TaskType.COMMON);
        List<Integer> expectedResult = Arrays.asList(
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> actualResult = collector.collectPrimes(1, 100, 5);
        assertTrue(expectedResult.containsAll(actualResult));
    }

    @Test
    public void shouldReturnListOfPrimeNumbersFoundByThreadsAndGatheredByIndividualLists() {
        collector = new ThreadPrimeNumberCollector(TaskType.INDIVIDUAL);
        List<Integer> expectedResult = Arrays.asList(
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> actualResult = collector.collectPrimes(1, 100, 5);
        assertTrue(expectedResult.containsAll(actualResult));
    }

    @Test
    public void shouldReturnListOfPrimeNumbersFoundByExecutorsAndGatheredToCommonList() {
        collector = new ExecutorPrimeNumberCollector(TaskType.COMMON);
        List<Integer> expectedResult = Arrays.asList(
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> actualResult = collector.collectPrimes(1, 100, 5);
        assertTrue(expectedResult.containsAll(actualResult));
    }

    @Test
    public void shouldReturnListOfPrimeNumbersFoundByExecutorsAndGatheredToCommonListByIndividualLists() {
        collector = new ExecutorPrimeNumberCollector(TaskType.INDIVIDUAL);
        List<Integer> expectedResult = Arrays.asList(
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        List<Integer> actualResult = collector.collectPrimes(1, 100, 5);
        assertTrue(expectedResult.containsAll(actualResult));
    }
}