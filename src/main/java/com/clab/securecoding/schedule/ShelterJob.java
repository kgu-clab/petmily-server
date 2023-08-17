package com.clab.securecoding.schedule;

import com.clab.securecoding.exception.ApiRequestFailedException;
import com.clab.securecoding.service.AnimalShelterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Slf4j
public class ShelterJob implements Job {

    private final AnimalShelterService animalShelterService;

    @Override
    public void execute(JobExecutionContext context) {
        log.info("Executing shelter job");
        try {
            animalShelterService.retrieveShelters();
        } catch (UnsupportedEncodingException e) {
            throw new ApiRequestFailedException();
        } catch (IOException e) {
            throw new ApiRequestFailedException();
        } catch (ParseException e) {
            throw new ApiRequestFailedException();
        }
        log.info("Shelter job completed");
    }

}
