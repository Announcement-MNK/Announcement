package com.example.turboaz.services;

import com.example.turboaz.utils.SchedulerUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerServiceImpl implements SchedulerService{
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    private Scheduler scheduler;

    public SchedulerServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void schedule(Class<? extends Job> jobClass) {
        JobDetail jobDetail = SchedulerUtil.buildJobDetail(jobClass);
        Trigger trigger = SchedulerUtil.buildTrigger(jobClass);
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage());
        }
    }


    @PreDestroy
    @Override
    public void destroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage());
        }
    }
}
