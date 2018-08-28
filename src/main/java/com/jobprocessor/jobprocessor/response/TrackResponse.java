package com.jobprocessor.jobprocessor.response;

import com.jobprocessor.jobprocessor.enums.Status;

public class TrackResponse {

    private String jobId;
    private int totalSteps;
    private int completedSteps;
    private Status status;
    String errorMessage;

    public TrackResponse(String jobId, int totalSteps, int completedSteps, Status status) {
        this.jobId = jobId;
        this.totalSteps = totalSteps;
        this.completedSteps = completedSteps;
        this.status = status;
    }

    public TrackResponse(Status status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getJobId() {
        return jobId;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public int getCompletedSteps() {
        return completedSteps;
    }

    public Status getStatus() {
        return status;
    }
}
