package com.jobprocessor.jobprocessor.processor;

import com.jobprocessor.jobprocessor.entities.Task;
import com.jobprocessor.jobprocessor.enums.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.BlockingDeque;

public class TaskProcessor implements Runnable {

    BlockingDeque<Task> taskQueue;

    public TaskProcessor(BlockingDeque<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (true) {
            Task subjob = null;
            try {
                subjob = taskQueue.take();
                subjob.setStatus(Status.INPROGRESS);
                readURLContent(subjob.getUrl());
                subjob.setStatus(Status.COMPLETED);
                subjob.getJob().incrementCompletedSteps();
            } catch (Exception e) {
                subjob.setStatus(Status.FAILED);
                subjob.setErrorMessage(e.getMessage());
                subjob.getJob().markFailed();
            }
        }

    }

    private void readURLContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
