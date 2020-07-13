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
    @GetMapping("/list-by-service/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsByService(@PathVariable(name = "id") int id, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals(2)) // 2 - employee
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return  ResponseEntity.ok(serviceDetailsService.searchByServiceId(id));
    }

    @GetMapping("/list-by-client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsByClient(@PathVariable(name = "id") int id, HttpSession session)
    {
        //може да използваме този request и за Операторите и за Клиентите да си видяд актривните услуги и
        //оставащи минути, Мbs и смс-и.
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
