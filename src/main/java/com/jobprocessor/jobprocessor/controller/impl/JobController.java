package com.jobprocessor.jobprocessor.controller.impl;

import com.jobprocessor.jobprocessor.request.JobRequest;
import com.jobprocessor.jobprocessor.response.JobResponse;
import com.jobprocessor.jobprocessor.response.TrackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface JobController {

    JobResponse submitJob(JobRequest jobRequest);

    TrackResponse trackJob(String jobId);

}
