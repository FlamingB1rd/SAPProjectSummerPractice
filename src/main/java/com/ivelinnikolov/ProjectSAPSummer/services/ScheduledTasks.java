package com.ivelinnikolov.ProjectSAPSummer.services;

import com.ivelinnikolov.ProjectSAPSummer.models.ServiceDetails;
import com.ivelinnikolov.ProjectSAPSummer.repository.ServiceDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    ServiceDetailsRepository serviceDetailsRepository;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    // "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]"
    // for testing use: 0/20 * * * * ? - Every 20 seconds
    @Scheduled(cron = "0 0 12 * * ?") //12PM Всеки ден
    public void checkActivity()
    {
        List<ServiceDetails> all = serviceDetailsRepository.findAll();
        for(ServiceDetails single : all)
        {
            LocalDate endDate = LocalDate.parse(single.getPaymentDue());
            LocalDate currentDay = LocalDate.now();
            if((single.getAvailableMbs() == 0 && single.getAvailableMinutes() == 0 && single.getAvailableNumberOfMessages() == 0) || currentDay.isAfter(endDate))
            {
                single.setStatus("Inactive");
                serviceDetailsRepository.save(single);
            }
        }
        log.info("All statuses have been updated for the day.");

    }


}
