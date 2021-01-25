package com.projectMeeting4U.main.springboot.User.service;

import com.projectMeeting4U.main.springboot.User.dto.NewUserRequest;
import com.projectMeeting4U.main.springboot.User.dto.NewUserResponse;
import com.projectMeeting4U.main.springboot.User.dto.UserResponse;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public NewUserResponse setUser(NewUserRequest newUserRequest) {
        User user = new User(
                newUserRequest.getUserId(),
                newUserRequest.getName(),
                passwordEncoder.encode(newUserRequest.getPassword()),
                newUserRequest.getEmail(),
                newUserRequest.getPhoneNumber(),
                newUserRequest.getHomeAddress(),
                Collections.singletonList("ROLE_USER") // 권한 부여
        );

        try {
            userRepository.save(user);
            NewUserResponse newUserResponse = new NewUserResponse("true");
            return newUserResponse;
        } catch (Exception e) {
            NewUserResponse newUserResponse = new NewUserResponse("false");
            return newUserResponse;
        }

    }

    @Override
    public UserResponse getUser(User user) {
        UserResponse userResponse = new UserResponse();

        if (user == null) {
            userResponse.setResult("false");
        } else {
            userResponse.setResult("true");
            userResponse.setUserId(user.getUserId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setPhoneNumber(user.getPhoneNumber());
            userResponse.setHomeAddress(user.getHomeAddress());
            userResponse.setCurrentLocation(user.getCurrentLocation());
            userResponse.setCreatedAt(user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            userResponse.setUpdatedAt(user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        return userResponse;
    }

}
