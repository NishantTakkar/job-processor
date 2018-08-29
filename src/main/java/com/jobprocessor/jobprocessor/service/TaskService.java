package com.jobprocessor.jobprocessor.service;

import com.jobprocessor.jobprocessor.entities.Task;
import com.jobprocessor.jobprocessor.exceptions.ValidationException;

import java.util.List;

public interface TaskService {

    void submitTasks(List<Task>tasks) throws ValidationException;

}
