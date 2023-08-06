package com.clab.securecoding.config;

import com.clab.securecoding.schedule.ShelterJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class QuartzConfig {

    @Bean
    public JobDetail shelterJobDetail() {
        return JobBuilder.newJob(ShelterJob.class)
                .withIdentity("shelterJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger shelterTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(24)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(shelterJobDetail())
                .withIdentity("shelterTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(shelterJobDetail(), shelterTrigger());
        return scheduler;
    }

}
