package com.ivelinnikolov.ProjectSAPSummer.controllers;

import com.ivelinnikolov.ProjectSAPSummer.models.User;
import com.ivelinnikolov.ProjectSAPSummer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //-------------------------------------------- GET REQUESTS --------------------------------------------

    @PreAuthorize("hasAnyRole('OPERATOR')")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAccounts()
    {
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping("/user{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAccount(@PathVariable(name = "id") int id)
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
    @PreAuthorize("hasAnyRole('OPERATOR')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userService.createNewAccount(user));
    }

    /*@PostMapping("/login")
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
    }*/
}
