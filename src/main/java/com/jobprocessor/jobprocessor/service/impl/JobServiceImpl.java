package com.jobprocessor.jobprocessor.service.impl;


import com.jobprocessor.jobprocessor.entities.Job;
import com.jobprocessor.jobprocessor.entities.Task;
import com.jobprocessor.jobprocessor.exceptions.NotFoundException;
import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.request.JobRequest;
import com.jobprocessor.jobprocessor.response.TrackResponse;
import com.jobprocessor.jobprocessor.service.JobService;
import com.jobprocessor.jobprocessor.service.TaskService;
import com.jobprocessor.jobprocessor.service.UniqueKeyGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {


    @Autowired
    UniqueKeyGenerationService uniqueKeyGenerationService;
    @Autowired
    TaskService taskService;

    Map<String, Job> jobMap = new HashMap<>();

    public JobServiceImpl() {
    }

    public String processJob(JobRequest jobRequest) throws ValidationException {
        if (Objects.isNull(jobRequest) || Objects.isNull(jobRequest.getUrlList()) || jobRequest.getUrlList().isEmpty()) {
            throw new ValidationException();
        }

        List<Task> tasks = new ArrayList<>();
        String jobId = uniqueKeyGenerationService.getUniqueJobId();
        Job job = new Job(jobId, tasks, jobRequest.getUrlList().size());  //total steps count is number of Urls which is equal to number of subjobs spawned

        for (String url : jobRequest.getUrlList()) {
            Long taskSequenceNumber = 1l;
            String subJobId = uniqueKeyGenerationService.getTaskIdForJobId(jobId, taskSequenceNumber);
            tasks.add(new Task(subJobId, url, job));
            taskSequenceNumber++;
        }

        jobMap.put(job.getJobId(), job);
        taskService.submitTasks(tasks);
        return job.getJobId();
    }

    public TrackResponse trackJob(String jobId) throws NotFoundException {
        Job job = jobMap.get(jobId);
        if (Objects.isNull(job))
            throw new NotFoundException();
        TrackResponse trackResponse = new TrackResponse(job.getJobId(), job.getTotalSteps(), job.getCompletedSteps(), job.getStatus());
        return trackResponse;
    }


}
