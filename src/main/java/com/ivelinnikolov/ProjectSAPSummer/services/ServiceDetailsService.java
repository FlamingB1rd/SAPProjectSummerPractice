package com.ivelinnikolov.ProjectSAPSummer.services;

import com.ivelinnikolov.ProjectSAPSummer.exceptions.NoSuchDetailsException;
import com.ivelinnikolov.ProjectSAPSummer.models.OfferedServices;
import com.ivelinnikolov.ProjectSAPSummer.models.ServiceDetails;
import com.ivelinnikolov.ProjectSAPSummer.models.User;
import com.ivelinnikolov.ProjectSAPSummer.repository.ServiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public boolean serviceIsActive(int clientId, int serviceId)
    {
        ServiceDetails details = getDetailsById(serviceId);
        if(details.getUserId() == clientId)
        {
            return true;
        }
        return false;
    }

    public ServiceDetails

    public String addServiceToUser(int clientId, int serviceId)
    {
        if(!serviceIsActive(clientId, serviceId))
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

    public ServiceDetails getDetailsById(int id)
    {
        return serviceDetailsRepository.findById(id).orElseGet(() -> {
            try
            {
                throw new NoSuchDetailsException();
            }
            catch (NoSuchDetailsException e)
            {
                e.getMessage();
                return null;
            }
        });
    }
}
