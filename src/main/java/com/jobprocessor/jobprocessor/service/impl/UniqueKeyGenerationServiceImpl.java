package com.jobprocessor.jobprocessor.service.impl;

import com.jobprocessor.jobprocessor.exceptions.ValidationException;
import com.jobprocessor.jobprocessor.service.UniqueKeyGenerationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UniqueKeyGenerationServiceImpl implements UniqueKeyGenerationService {

    private AtomicLong uniqueJobId;

    public UniqueKeyGenerationServiceImpl() {
        this.uniqueJobId = new AtomicLong(0);
    }

    public String getUniqueJobId() {

        Long id = uniqueJobId.incrementAndGet();
        return "JOB_" + String.valueOf(id);
    }

    public String getTaskIdForJobId(String jobId, Long count) throws ValidationException {

        if(count<1)
            throw new ValidationException();
        List<String> job = Arrays.asList(jobId.split("_"));
        return "TASK_" + job.get(1) +"_"+ String.valueOf(count);
    }


}
