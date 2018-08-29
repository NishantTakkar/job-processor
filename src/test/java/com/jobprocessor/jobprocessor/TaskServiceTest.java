package com.jobprocessor.jobprocessor;

import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.service.impl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class TaskServiceTest {

    @InjectMocks
    TaskServiceImpl taskService;

    public TaskServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionForSubmittingNull() throws ValidationException {
      taskService.submitTasks(null);
    }


}
