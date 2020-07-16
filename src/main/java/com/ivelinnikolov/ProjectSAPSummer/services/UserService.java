package com.ivelinnikolov.ProjectSAPSummer.services;

import com.ivelinnikolov.ProjectSAPSummer.exceptions.NoSuchUserException;
import com.ivelinnikolov.ProjectSAPSummer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ivelinnikolov.ProjectSAPSummer.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll()
    {
        return userRepository.findAll();
    }

    /*public ResponseEntity<?> logIn(User user, HttpSession session)
    {
        User validateUser = userRepository.findByUsername(user.getUsername());

        if (validateUser == null || !user.getPassword().equals(validateUser.getPassword()))
        {
            return ResponseEntity.status(401).body("The username or password is not correct");
        }

        session.setAttribute("userId", validateUser.getId());
        session.setAttribute("userRole", validateUser.getAccountType());

        return ResponseEntity.ok("User " + validateUser.getUsername() + " successfully logged in as " + validateUser.getAccountType());
    }*/

    public User createNewAccount(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getAccountById(int Id)
    {
        return userRepository.findById(Id).orElseGet(() -> {
            try
            {
                throw new NoSuchUserException();
            }
            catch (NoSuchUserException e)
            {
                e.getMessage();
                return null;
            }
        });
    }
}
