package com.jobprocessor.jobprocessor.response;

import com.jobprocessor.jobprocessor.enums.Status;

import java.util.List;

public class JobResponse {

    String jobId;
    List<String> urlList;
    Status status;
    String errorMessage;

    public JobResponse(String jobId, List<String> urlList) {
        this.jobId = jobId;
        this.urlList = urlList;
    }

    public JobResponse(Status status,String errorMessage) {
        this.status = status;
        this.errorMessage=errorMessage;
    }


    public String getJobId() {
        return jobId;
    }

    public List<String> getUrlList() {
        return urlList;
    }

}
