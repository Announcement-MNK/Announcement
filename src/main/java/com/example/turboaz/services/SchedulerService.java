package com.example.turboaz.services;

import org.quartz.Job;

public interface SchedulerService {
    void schedule(Class<? extends Job> jobClass);
    void destroy();
}
