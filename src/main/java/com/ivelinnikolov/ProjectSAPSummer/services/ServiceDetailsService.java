package com.ivelinnikolov.ProjectSAPSummer.services;

import com.ivelinnikolov.ProjectSAPSummer.models.OfferedServices;
import com.ivelinnikolov.ProjectSAPSummer.models.ServiceDetails;
import com.ivelinnikolov.ProjectSAPSummer.models.User;
import com.ivelinnikolov.ProjectSAPSummer.repository.ServiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    //for operators
    public List<ServiceDetails> listUnpaidClients()
    {
        List<ServiceDetails> all = serviceDetailsRepository.findAll();
        List<ServiceDetails> valid = new ArrayList<>();
        for(ServiceDetails single : all)
        {
            LocalDate endDate = LocalDate.parse(single.getPaymentDue());
            LocalDate currentDay = LocalDate.now();
            if (single.getPaymentAmount() > 0 && currentDay.isAfter(endDate))
            {
                valid.add(single);
            }
        }

        return valid;
    }

    //for clients
    public List<ServiceDetails> listClintData(int clientId)
    {
        List<ServiceDetails> all = searchByClientId(clientId);
        List<ServiceDetails> valid = new ArrayList<>();
        for(ServiceDetails single : all)
        {
            LocalDate endDate = LocalDate.parse(single.getPaymentDue());
            LocalDate currentDay = LocalDate.now();
            if (single.getPaymentAmount() > 0 && !currentDay.isAfter(endDate))
            {
                valid.add(single);
            }
        }
        return valid;
    }

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
            System.out.println(all);
            if (single.getServiceId() == serviceId)
            {
                valid.add(single);
            }
            System.out.println(valid);
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
        LocalDate currentDate = LocalDate.now();

        ServiceDetails details = new ServiceDetails();

        details.setUserId(client.getId());
        details.setServiceId(service.getId());
        details.setPaymentAmount(service.getPaymentPerMonth());
        details.setPaymentDue(currentDate.plusMonths(1).toString());
        details.setPaymentStatus("Unpaid");
        details.setAvailableMinutes(service.getOfferedMinutes());
        details.setAvailableNumberOfMessages(service.getOfferedNumberOfMessages());
        details.setAvailableMbs(service.getOfferedMbs());

        serviceDetailsRepository.save(details);
        return "Service added to user successfully!";
    }

    public List<ServiceDetails> listAll()
    {
        return serviceDetailsRepository.findAll();
    }
}
