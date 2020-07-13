package com.ivelinnikolov.ProjectSAPSummer.controllers;

import com.ivelinnikolov.ProjectSAPSummer.models.OfferedServices;
import com.ivelinnikolov.ProjectSAPSummer.services.OfferedServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class OfferedServicesController
{
    @Autowired
    OfferedServicesService offeredServicesService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllProducts()
    {
        return ResponseEntity.ok(offeredServicesService.listAll());
    }
}
