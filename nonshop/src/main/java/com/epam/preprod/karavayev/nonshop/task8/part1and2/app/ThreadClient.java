package com.epam.preprod.karavayev.nonshop.task8.part1and2.app;

import com.epam.preprod.karavayev.model.console.ConsoleHelper;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.PrimesCollectable;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.director.CollectorDirector;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.type.TaskType;

import java.util.Collections;
import java.util.List;

public class ThreadClient {

    private ConsoleHelper console;
    private CollectorDirector collectorDirector;

    private ThreadClient() {
        this.console = new ConsoleHelper();
        this.collectorDirector = new CollectorDirector();
    }

    public static void main(String[] args) {
        new ThreadClient().start();
    }

    private void start() {
        console.promptMessage("Welcome to prime numbers finder app!!");
        console.promptMessage("Would you prefer Thread(T) or Executors(E)?");
        String collectorInput = console.takeInput();
        String collectorArg = collectorInput.equalsIgnoreCase("T") ?
                              "thread" : collectorInput.equalsIgnoreCase("E") ?
                                         "executors" : "";
        console.promptMessage("Collect to common collections by numbers(N) or by lists(L)?");
        String taskInput = console.takeInput();
        TaskType taskType = taskInput.equalsIgnoreCase("N") ?
                            TaskType.COMMON : taskInput.equalsIgnoreCase("L") ?
                                              TaskType.INDIVIDUAL : null;
        PrimesCollectable collector = collectorDirector.getCollector(collectorArg, taskType);
        console.promptMessage("Enter range of numbers(two numbers).");
        int startRange = console.takeInteger();
        int endRange = console.takeInteger();
        console.promptMessage("How many threads do you want to involve?");
        int numberOfThreads = console.takeInteger();
        List<Integer> result = collector.collectPrimes(startRange, endRange, numberOfThreads);
        console.promptMessage("The result: ");

        Collections.sort(result);
        result.forEach(n-> System.out.print(n + " "));

    }
}
