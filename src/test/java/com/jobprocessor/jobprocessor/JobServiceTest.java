package com.jobprocessor.jobprocessor;

import com.jobprocessor.jobprocessor.exceptions.NotFoundException;
import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.request.JobRequest;
import com.jobprocessor.jobprocessor.response.TrackResponse;
import com.jobprocessor.jobprocessor.service.TaskService;
import com.jobprocessor.jobprocessor.service.UniqueKeyGenerationService;
import com.jobprocessor.jobprocessor.service.impl.JobServiceImpl;
import com.jobprocessor.jobprocessor.service.impl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class JobServiceTest {

    @InjectMocks
    JobServiceImpl jobService;

    @Mock
    UniqueKeyGenerationService uniqueKeyGenerationService;

    @Mock
    TaskService taskService;

    public JobServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnJobId() throws ValidationException {

        JobRequest jobRequest=new JobRequest();
        jobRequest.setUrlList(Arrays.asList("www.google.com"));
        Mockito.when(uniqueKeyGenerationService.getUniqueJobId()).thenReturn("JOB_1");
        Mockito.when(uniqueKeyGenerationService.getTaskIdForJobId("JOB_1",1l)).thenReturn("TASK_1_1");
        String jobId = jobService.processJob(jobRequest);
        Assert.assertEquals(jobId,"JOB_1");

    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionForEmptyURLList() throws ValidationException {

        JobRequest jobRequest = new JobRequest();
        Mockito.when(uniqueKeyGenerationService.getUniqueJobId()).thenReturn("JOB_1");
        Mockito.when(uniqueKeyGenerationService.getTaskIdForJobId("JOB_1", 1l)).thenReturn("TASK_1_1");
        String jobId = jobService.processJob(jobRequest);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionForNullJobRequest() throws ValidationException {

        JobRequest jobRequest = null;
        Mockito.when(uniqueKeyGenerationService.getUniqueJobId()).thenReturn("JOB_1");
        Mockito.when(uniqueKeyGenerationService.getTaskIdForJobId("JOB_1", 1l)).thenReturn("TASK_1_1");
        String jobId = jobService.processJob(jobRequest);
    }

    @Test
    public void shouldReturnValidjobIdForTracking() throws ValidationException, NotFoundException {

        JobRequest jobRequest=new JobRequest();
        jobRequest.setUrlList(Arrays.asList("www.google.com"));
        Mockito.when(uniqueKeyGenerationService.getUniqueJobId()).thenReturn("JOB_1");
        Mockito.when(uniqueKeyGenerationService.getTaskIdForJobId("JOB_1", 1l)).thenReturn("TASK_1_1");
        String jobId = jobService.processJob(jobRequest);
        TrackResponse trackResponse = jobService.trackJob("JOB_1");
        Assert.assertEquals(trackResponse.getJobId(),"JOB_1");

    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowExceptionForTrackingInvalidJobId() throws ValidationException, NotFoundException {

        JobRequest jobRequest=new JobRequest();
        jobRequest.setUrlList(Arrays.asList("www.google.com"));
        Mockito.when(uniqueKeyGenerationService.getUniqueJobId()).thenReturn("JOB_1");
        Mockito.when(uniqueKeyGenerationService.getTaskIdForJobId("JOB_1", 1l)).thenReturn("TASK_1_1");
        String jobId = jobService.processJob(jobRequest);
        TrackResponse trackResponse = jobService.trackJob("JOB_2");
    }


}
