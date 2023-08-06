package com.clab.securecoding.schedule;

import com.clab.securecoding.service.AnimalShelterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

@RequiredArgsConstructor
@Slf4j
public class ShelterJob implements Job {

    private final AnimalShelterService animalShelterService;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Executing shelter job");
        animalShelterService.retrieveShelters();
        log.info("Shelter job completed");
    }

}
