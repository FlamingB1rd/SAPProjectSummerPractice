package com.ivelinnikolov.ProjectSAPSummer.controllers;

import com.ivelinnikolov.ProjectSAPSummer.body_request_models.LoginForm;
import com.ivelinnikolov.ProjectSAPSummer.models.User;
import com.ivelinnikolov.ProjectSAPSummer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAccounts(HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals("operator"))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/individual/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAccount(@PathVariable(name = "id") int id, HttpSession session)
    {

        return  ResponseEntity.ok(userService.getAccountById(id));
    }

    //-------------------------------------------- POST REQUESTS --------------------------------------------

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAccount(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userService.createNewAccount(user));
    }

    //The operator can add users himself:
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody User user, HttpSession session)
    {
        if (session.getAttribute("userId") == null)
        {
            return ResponseEntity.status(401).body("Unauthorized!");
        }
        else if (!session.getAttribute("userRole").equals("operator"))
        {
            return ResponseEntity.status(403).body("Request unavailable for this account.");
        }

        return ResponseEntity.ok(userService.createNewAccount(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginForm loginForm, HttpSession session)
    {
        User userValidate = new User();
        userValidate.setUsername(loginForm.getUsername());
        userValidate.setPassword(loginForm.getPassword());
        return userService.logIn(userValidate, session);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session)
    {
        session.invalidate();

        return ResponseEntity.ok("Logged out successfully!");
    }
}
