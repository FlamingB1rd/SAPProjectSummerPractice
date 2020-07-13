package com.ivelinnikolov.ProjectSAPSummer.services;

import com.ivelinnikolov.ProjectSAPSummer.exceptions.NoSuchDetailsException;
import com.ivelinnikolov.ProjectSAPSummer.models.OfferedServices;
import com.ivelinnikolov.ProjectSAPSummer.models.ServiceDetails;
import com.ivelinnikolov.ProjectSAPSummer.models.User;
import com.ivelinnikolov.ProjectSAPSummer.repository.ServiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDetailsService
{
    @Autowired
    private ServiceDetailsRepository serviceDetailsRepository;

    @Autowired
    private OfferedServicesService offeredServicesService;

    @Autowired
    private UserService userService;


    public List<ServiceDetails> searchByClientId(int clientId)
    {
        List<ServiceDetails> all = serviceDetailsRepository.findAll();
        List<ServiceDetails> valid = new ArrayList<>();
        for(ServiceDetails single : all)
        {
            if (single.getUserId() == clientId)
            {
                valid.add(single);
            }
        }

        return valid;
    }

    public List<ServiceDetails> searchByServiceId(int serviceId)
    {
        List<ServiceDetails> all = serviceDetailsRepository.findAll();
        List<ServiceDetails> valid = new ArrayList<>();
        for(ServiceDetails single : all)
        {
            if (single.getServiceId() == serviceId)
            {
                valid.add(single);
            }
        }

        return valid;
    }

    public boolean serviceExists(int clientId, int serviceId)
    {
        //find the one that matches having both the client Id and service Id (there will be ONLY one such form)
        List<ServiceDetails> all = serviceDetailsRepository.findAll();
        for(ServiceDetails single : all)
        {
            if (single.getUserId() == clientId && single.getServiceId() == serviceId)
            {
                return true;
            }
        }
        return false;
    }

    public String addServiceToUser(int clientId, int serviceId)
    {
        if(!serviceExists(clientId, serviceId))
        {
            return "Service already active for this user!";
        }

        OfferedServices service = offeredServicesService.getServiceById(serviceId);
        User client = userService.getAccountById(clientId);

        ServiceDetails details = new ServiceDetails();

        details.setUserId(client.getId());
        details.setServiceId(service.getId());
        details.setPaymentAmount(service.getPaymentPerMonth());
        //details.setPaymentDue(); todo: replace placeholder
        //details.setPaymentStatus(); todo: replace placeholder
        details.setAvailableMinutes(service.getOfferedMinutes());
        details.setAvailableNumberOfMessages(service.getOfferedNumberOfMessages());
        details.setAvailableMbs(service.getOfferedMbs());

        //todo: add exceptions

        serviceDetailsRepository.save(details);
        return "Service added to user successfully!";
    }

    public List<ServiceDetails> listAll()
    {
        return serviceDetailsRepository.findAll();
    }
}
