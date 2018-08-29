package com.jobprocessor.jobprocessor.service.impl;

import com.jobprocessor.jobprocessor.entities.Task;
import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.processor.TaskProcessor;
import com.jobprocessor.jobprocessor.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

@Service
public class TaskServiceImpl implements TaskService{

    BlockingDeque<Task> taskQueue;
    ExecutorService threadPoolExecutor;

    private static final int THREAD_COUNT = 5;

    public TaskServiceImpl() {
        taskQueue = new LinkedBlockingDeque<>();
        threadPoolExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
        IntStream.range(0, THREAD_COUNT).forEach(value -> threadPoolExecutor.submit((new TaskProcessor(taskQueue))));
    }

    public void submitTasks(List<Task> tasks) throws ValidationException {
        if(Objects.isNull(tasks) && tasks.isEmpty()){
            throw new ValidationException();
        }
        taskQueue.addAll(tasks);
    }



}
