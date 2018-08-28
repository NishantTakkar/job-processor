package com.jobprocessor.jobprocessor.entities;

import com.jobprocessor.jobprocessor.enums.Status;

import java.util.List;

public class Job {

    private String jobId;
    private List<Task> tasks;
    private int totalSteps;
    private int completedSteps;
    private Status status;


    public Job(String jobId, List<Task> tasks, int totalSteps) {
        this.jobId = jobId;
        this.tasks = tasks;
        this.totalSteps = totalSteps;
        this.completedSteps = 0;
        this.status = Status.QUEUED;
    }

    public List<Task> getTasks() {
        return tasks;
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

    public synchronized void incrementCompletedSteps() {
        completedSteps++;
        if (status == Status.QUEUED) {
            status = Status.INPROGRESS;
        }
        if (status == Status.INPROGRESS && completedSteps == totalSteps) {
            status = Status.COMPLETED;
        }

    }

    public synchronized void markFailed() {
        if (status != Status.COMPLETED) {
            status = Status.FAILED;
        }
    }

}
