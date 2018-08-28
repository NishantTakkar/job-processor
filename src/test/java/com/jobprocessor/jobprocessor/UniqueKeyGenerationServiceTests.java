package com.jobprocessor.jobprocessor;

import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.service.impl.UniqueKeyGenerationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UniqueKeyGenerationServiceTests {


    @InjectMocks
    UniqueKeyGenerationServiceImpl uniqueKeyGenerationService;

    public UniqueKeyGenerationServiceTests() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGenerateUniqueKeys(){
        String id1=uniqueKeyGenerationService.getUniqueJobId();
        String id2=uniqueKeyGenerationService.getUniqueJobId();
        Assert.assertNotEquals(id1,id2);
    }

    @Test
    public void shouldGenerateValidTaskKey() throws ValidationException {
        String id1=uniqueKeyGenerationService.getTaskIdForJobId("JOB_1",1l);
        Assert.assertEquals(id1,"TASK_1_1");
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionForInvalidCount() throws ValidationException {
        String id1=uniqueKeyGenerationService.getTaskIdForJobId("JOB_1",0l);
        Assert.assertEquals(id1,"TASK_1_1");
    }





}
