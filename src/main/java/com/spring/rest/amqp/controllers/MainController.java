package com.spring.rest.amqp.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.spring.rest.amqp.broking.BrokingDispatcher;
import com.spring.rest.amqp.broking.event.ProfileCreatedEvent;
import com.spring.rest.amqp.errors.exceptions.UserNotFoundException;
import com.spring.rest.amqp.models.Authority;
import com.spring.rest.amqp.models.User;
import com.spring.rest.amqp.repositories.AuthorityRepository;
import com.spring.rest.amqp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MainController {

    @Autowired
    private BrokingDispatcher dispatcher;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorityRepository authorityRepo;

    @PostMapping
    @ResponseBody
    public User createUser(@RequestBody User user) {
        User newUser = new User(user.getEmail(), encoder.encode(user.getPassword()));
        User savedUser = userRepo.save(newUser);
        authorityRepo.save(new Authority(savedUser.getEmail(), "USER", savedUser));

        return savedUser;
    }

    @GetMapping
    @ResponseBody
    public Optional<User> getUser(@RequestParam("id") String id) {
        Optional<User> user = userRepo.findById(id);

        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User with given id not found");
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/profile")
    @ResponseBody
    public boolean createProfile(@RequestBody Map<String, String> body) {
        dispatcher.dispatchProfileCreatedEvent(new ProfileCreatedEvent(body.get("email"), body.get("firstName"),
                body.get("lastName"), Integer.parseInt(body.get("age")), body.get("occupation")));

        return true;
    }

}