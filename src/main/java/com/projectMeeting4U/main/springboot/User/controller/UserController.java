package com.projectMeeting4U.main.springboot.User.controller;

import com.projectMeeting4U.main.springboot.Location.controller.LocationController;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRedisRepository;
import com.projectMeeting4U.main.springboot.Location.service.CurrentLocationRedisService;
import com.projectMeeting4U.main.springboot.Security.JwtTokenProvider;
import com.projectMeeting4U.main.springboot.User.dto.*;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import com.projectMeeting4U.main.springboot.User.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Api(tags = {"Users"})
@RestController
@RequestMapping(path = "api")
public class UserController {
    @Autowired
    LocationController locationController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentLocationRedisRepository currentLocationRedisRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CurrentLocationRedisService currentLocationRedisService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public UserController(PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello") // Test Code
    public String hello() { return "hello world!"; }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "Get All User's Info", notes = "모든 회원 정보 조회")
    @GetMapping("/users") // Get All Users Info
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @ApiOperation(value = "Create New User Obj", notes = "회원 가입")
    @PostMapping("/sign-up")
    @Transactional
    public NewUserResponse singUp(@ApiParam(value = "회원 가입", required = true) @Valid @RequestBody NewUserRequest newUserRequest) { // Create New User Data
        return userService.setUser(newUserRequest);
    }

    @ApiOperation(value = "Login", notes = "로그인")
    @PostMapping("/sign-in")
    @Transactional
    public LoginResponse signIn(@ApiParam(value = "id", required = true) @Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getUserId());
        return userService.getMeetingInfo(user, loginRequest);

    }


    @ApiOperation(value = "Check ID exist already", notes = "아이디 중복 조회")
    @GetMapping("/sign-up/{id}")
    public String checkUserId(@PathVariable String id) { // Check ID exist already
        if(userRepository.findByUserId(id) == null)
            return "true";

        return "false";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "Get User Information", notes = "유저 정보 조회")
    @GetMapping("/user/{id}")
    @Transactional
    public UserResponse getUser(@PathVariable String id) { // Get {id} User Information
        User user = userRepository.findByUserId(id);
        return userService.getUser(user);
    }



}
