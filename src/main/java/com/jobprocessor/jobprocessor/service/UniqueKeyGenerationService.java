package com.jobprocessor.jobprocessor.service;

import com.jobprocessor.jobprocessor.exceptions.ValidationException;

public interface UniqueKeyGenerationService {

    String getUniqueJobId();

    String getTaskIdForJobId(String jobId, Long count) throws ValidationException;

}
