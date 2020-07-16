package com.ivelinnikolov.ProjectSAPSummer.controllers;

import com.ivelinnikolov.ProjectSAPSummer.body_request_models.AddServiceToClientById;
import com.ivelinnikolov.ProjectSAPSummer.services.ServiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
public class ServiceDetailsController
{
    @Autowired
    ServiceDetailsService serviceDetailsService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------
    @PreAuthorize("hasAnyRole('OPERATOR')")
    @GetMapping("/unpaid-details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listUnpaidClients()
    {
        return  ResponseEntity.ok(serviceDetailsService.listUnpaidClients());
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("/client{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listClientPaymentDetails(@PathVariable(name = "id") int id)
    {
        return  ResponseEntity.ok(serviceDetailsService.listClintData(id));
    }

    @PreAuthorize("hasAnyRole('OPERATOR')")
    @GetMapping("/list-by-service/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsByService(@PathVariable(name = "id") int id)
    {
        return  ResponseEntity.ok(serviceDetailsService.searchByServiceId(id));
    }

    @PreAuthorize("hasAnyRole('OPERATOR')")
    @GetMapping("/list-by-client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsByClient(@PathVariable(name = "id") int id)
    {
        return  ResponseEntity.ok(serviceDetailsService.searchByClientId(id));
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("/add-service-to-client")
    public ResponseEntity<?> addServiceToClientById(@RequestBody AddServiceToClientById action)
    {
        String response = serviceDetailsService.addServiceToUser(action.getClientId(), action.getServiceId());

        return ResponseEntity.ok(response);
    }
}
