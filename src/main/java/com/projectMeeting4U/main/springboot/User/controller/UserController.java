package com.projectMeeting4U.main.springboot.User.controller;

import com.projectMeeting4U.main.springboot.Location.controller.LocationController;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;
import com.projectMeeting4U.main.springboot.User.dto.NewUserRequest;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    LocationController locationController;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() { return "hello world!"; }

    @GetMapping("/users")
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @PostMapping("/user")
    @Transactional
    public User newUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        CurrentLocation currentLocation = locationController.newCurrentLoacion();
        User user = new User(
                newUserRequest.getUserId(),
                newUserRequest.getName(),
                newUserRequest.getHomeAddress(),
                currentLocation
        );

        userRepository.save(user);

        return user;
    }
}
