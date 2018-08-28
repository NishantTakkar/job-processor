package com.jobprocessor.jobprocessor.service;

import com.jobprocessor.jobprocessor.entities.Task;

import java.util.List;

public interface TaskService {

    void submitTasks(List<Task>tasks);

}
