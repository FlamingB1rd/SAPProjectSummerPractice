package com.ivelinnikolov.ProjectSAPSummer.controllers;


import com.ivelinnikolov.ProjectSAPSummer.body_request_models.AddServiceToClientById;
import com.ivelinnikolov.ProjectSAPSummer.services.ServiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/details")
public class ServiceDetailsController
{
    @Autowired
    ServiceDetailsService serviceDetailsService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------
    @GetMapping("/unpaid-details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listUnpaidClients(HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals("operator"))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return  ResponseEntity.ok(serviceDetailsService.listUnpaidClients());
    }

    @GetMapping("/client-details")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listClientPaymentDetails(HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals("customer"))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return  ResponseEntity.ok(serviceDetailsService.listClintData((int)session.getAttribute("userId")));
    }

    @GetMapping("/list-by-service/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsByService(@PathVariable(name = "id") int id, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals("operator"))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }
        return  ResponseEntity.ok(serviceDetailsService.searchByServiceId(id));
    }

    @GetMapping("/list-by-client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsByClient(@PathVariable(name = "id") int id, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals("operator"))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return  ResponseEntity.ok(serviceDetailsService.searchByClientId(id));
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/add-service-to-client")
    public ResponseEntity<?> addServiceToClientById(@RequestBody AddServiceToClientById action, HttpSession session)
    {
        String response = serviceDetailsService.addServiceToUser(action.getClientId(), action.getServiceId());

        return ResponseEntity.ok(response);
    }
}
