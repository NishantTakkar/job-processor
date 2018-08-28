package com.jobprocessor.jobprocessor.entities;

import com.jobprocessor.jobprocessor.enums.Status;

public class Task {

    private String id;
    private String url;
    private Job job;
    private Status status;
    private String errorMessage;

    public Task(String id, String url, Job job) {
        this.id = id;
        this.job = job;
        this.url = url;
        this.status = Status.QUEUED;
    }

    public Job getJob() {
        return job;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
