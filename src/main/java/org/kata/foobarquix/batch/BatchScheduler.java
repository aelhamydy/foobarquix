package org.kata.foobarquix.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job mainJob;

    public BatchScheduler(JobLauncher jobLauncher, Job mainJob) {
        this.jobLauncher = jobLauncher;
        this.mainJob = mainJob;
    }

    @Scheduled(fixedRate = 30000) // Run every 30,000 ms (30 sec)
    public void runBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            System.out.println("Starting scheduled batch job...");
            jobLauncher.run(mainJob, jobParameters);
            System.out.println("Batch job completed!");
        } catch (Exception e) {
            System.err.println("Error running batch job: " + e.getMessage());
        }
    }
}
