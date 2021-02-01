package com.projectMeeting4U.main.springboot.Meeting.controller;

import com.projectMeeting4U.main.springboot.Exception.ResourceNotFoundException;
import com.projectMeeting4U.main.springboot.Location.controller.LocationController;
import com.projectMeeting4U.main.springboot.Location.dto.NewDepartLocationRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewDestLocationRequest;
import com.projectMeeting4U.main.springboot.Location.entity.DepartLocation;
import com.projectMeeting4U.main.springboot.Location.entity.DestinationLocation;
import com.projectMeeting4U.main.springboot.Meeting.dto.MeetingResponse;
import com.projectMeeting4U.main.springboot.Meeting.dto.NewMeetingRequest;
import com.projectMeeting4U.main.springboot.Meeting.entity.*;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingRepository;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingUserRepository;
import com.projectMeeting4U.main.springboot.Meeting.service.MeetingService;
import com.projectMeeting4U.main.springboot.User.controller.UserController;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class MeetingController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    LocationController locationController;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MeetingUserRepository meetingUserRepository;

    @Autowired
    private MeetingService meetingService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping(path = "/meetings")
    public List<Meeting> getAllMeetings() { return meetingRepository.findAll(); }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping(path = "/meetings/{userId}")
    public MeetingResponse getMeetingByUserId(@PathVariable String userId) {
        MeetingResponse meetingList = meetingService.getMeetingList(userId);
        return meetingList;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping(path = "/meeting")
    @Transactional
    public Meeting newMeeting(@Valid @RequestBody NewMeetingRequest newMeetingRequest) {
        NewDestLocationRequest newDestLocationRequest = new NewDestLocationRequest((newMeetingRequest.getDestinationAddress()));
        NewDepartLocationRequest newDepartLocationRequest = new NewDepartLocationRequest();
        DestinationLocation newDestination = locationController.newDestLocation(newDestLocationRequest);
        DepartLocation newDepartLocation = locationController.newDepartLocation(newDepartLocationRequest);

        Meeting meeting = new Meeting(
                newMeetingRequest.getName(),
                newDestination,
                newMeetingRequest.getAppointmentTime(),
                MeetingState.MEETING_READY
        );

        meetingRepository.save(meeting);

        User hostUser = userRepository.findById(userRepository.findByUserId(newMeetingRequest.getUserId()).getId())
                .orElseThrow(() -> new ResourceNotFoundException("user", "user", HttpStatus.NOT_FOUND));

        MeetingUser meetingUser = new MeetingUser(
                meeting,
                hostUser,
                newDepartLocation,
                LocationSharingState.PRIVATE,
                MeetingUserType.HOST
        );
        meetingUserRepository.save(meetingUser);

        return meeting;
    }



}
