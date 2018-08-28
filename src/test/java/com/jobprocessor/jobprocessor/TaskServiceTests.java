package com.jobprocessor.jobprocessor;

import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.service.impl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class TaskServiceTests {

    @InjectMocks
    TaskServiceImpl taskService;

    public TaskServiceTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForSubmittingNull() {
      taskService.submitTasks(null);
    }


}
