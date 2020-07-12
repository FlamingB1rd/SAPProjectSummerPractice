package com.ivelinnikolov.ProjectSAPSummer.services;

import com.ivelinnikolov.ProjectSAPSummer.exceptions.NoSuchOfferedServiceException;
import com.ivelinnikolov.ProjectSAPSummer.exceptions.NoSuchUserException;
import com.ivelinnikolov.ProjectSAPSummer.models.OfferedServices;
import com.ivelinnikolov.ProjectSAPSummer.repository.OfferedServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferedServicesService
{
    @Autowired
    private OfferedServicesRepository offeredServicesRepository;

    public List<OfferedServices> listAll()
    {
        return offeredServicesRepository.findAll();
    }

    public OfferedServices getServiceById(int id)
    {
        return offeredServicesRepository.findById(id).orElseGet(() -> {
            try
            {
                throw new NoSuchOfferedServiceException();
            }
            catch (NoSuchOfferedServiceException e)
            {
                e.getMessage();
                return null;
            }
        });
    }
}
