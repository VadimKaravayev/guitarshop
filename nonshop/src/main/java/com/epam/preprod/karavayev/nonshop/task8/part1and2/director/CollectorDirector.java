package com.epam.preprod.karavayev.nonshop.task8.part1and2.director;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.ExecutorPrimeNumberCollector;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.PrimesCollectable;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.collectors.ThreadPrimeNumberCollector;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.type.TaskType;

import java.util.HashMap;
import java.util.Map;

public class CollectorDirector {

    private Map<String, PrimesCollectable> map = new HashMap<>();

    public PrimesCollectable getCollector(String collector, TaskType taskType) {
        map.put("thread", new ThreadPrimeNumberCollector(taskType));
        map.put("executors", new ExecutorPrimeNumberCollector(taskType));
        return map.get(collector);
    }

}
