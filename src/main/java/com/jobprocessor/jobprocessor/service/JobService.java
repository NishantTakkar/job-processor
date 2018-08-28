package com.jobprocessor.jobprocessor.service;

import com.jobprocessor.jobprocessor.exceptions.NotFoundException;
import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.request.JobRequest;
import com.jobprocessor.jobprocessor.response.TrackResponse;

public interface JobService {

    String processJob(JobRequest jobRequest) throws ValidationException;

    TrackResponse trackJob(String jobId) throws NotFoundException;

}
