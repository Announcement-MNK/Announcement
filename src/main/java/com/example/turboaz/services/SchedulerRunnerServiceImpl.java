package com.example.turboaz.services;

import com.example.turboaz.jobs.AutoPaymentJob;
import com.example.turboaz.jobs.NotifySubscriptionJob;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerRunnerServiceImpl implements SchedulerRunner{
    SchedulerServiceImpl scheduler;

    public SchedulerRunnerServiceImpl(SchedulerServiceImpl scheduler) {
        this.scheduler = scheduler;
    }

    @Bean
    @Override
    public void runJob() {
        scheduler.schedule(NotifySubscriptionJob.class);
        scheduler.schedule(AutoPaymentJob.class);
    }
}