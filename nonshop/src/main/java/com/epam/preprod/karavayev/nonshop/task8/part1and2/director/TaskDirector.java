package com.epam.preprod.karavayev.nonshop.task8.part1and2.director;

import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.CommonCollectionTask;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.IndividualCollectionTask;
import com.epam.preprod.karavayev.nonshop.task8.part1and2.task.type.TaskType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDirector {

    private Map<TaskType, Runnable> map = new HashMap<>();

    public TaskDirector(List<Integer> share, List<Integer> list) {
        map.put(TaskType.COMMON, new CommonCollectionTask(share, list));
        map.put(TaskType.INDIVIDUAL, new IndividualCollectionTask(share, list));
    }

    public Runnable getTask(TaskType taskType) {
        return map.get(taskType);
    }
}
