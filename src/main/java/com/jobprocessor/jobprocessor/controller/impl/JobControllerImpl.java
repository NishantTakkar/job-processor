package com.jobprocessor.jobprocessor.controller.impl;

import com.jobprocessor.jobprocessor.enums.Status;
import com.jobprocessor.jobprocessor.exceptions.NotFoundException;
import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.request.JobRequest;
import com.jobprocessor.jobprocessor.response.JobResponse;
import com.jobprocessor.jobprocessor.response.TrackResponse;
import com.jobprocessor.jobprocessor.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobControllerImpl implements JobController {

    @Autowired
    JobService jobService;

    @PostMapping("/submit")
    public JobResponse submitJob(@RequestBody JobRequest jobRequest) {

        String jobId = null;
        JobResponse jobResponse=null;
        try {
            jobId = jobService.processJob(jobRequest);
            jobResponse = new JobResponse(jobId, jobRequest.getUrlList());
        } catch (ValidationException e) {
            jobResponse = new JobResponse(Status.FAILED,e.getMessage());
        }
        return jobResponse;
    }

    @GetMapping("/track/{id}")
    public TrackResponse trackJob(@PathVariable(value = "id") String id) {
        TrackResponse trackResponse=null;
        try {
            return jobService.trackJob(id);
        } catch (NotFoundException e) {
            return new TrackResponse(Status.FAILED,e.getMessage());
        }
    }


}
