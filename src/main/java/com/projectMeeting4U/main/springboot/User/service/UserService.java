package com.projectMeeting4U.main.springboot.User.service;

import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisRequest;
import com.projectMeeting4U.main.springboot.Location.service.CurrentLocationRedisService;
import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingUserRepository;
import com.projectMeeting4U.main.springboot.Meeting.service.MeetingService;
import com.projectMeeting4U.main.springboot.Security.JwtTokenProvider;
import com.projectMeeting4U.main.springboot.User.dto.*;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetingUserRepository meetingUserRepository;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private CurrentLocationRedisService currentLocationRedisService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public UserService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public NewUserResponse setUser(NewUserRequest newUserRequest) {
        NewCurrentLocationRedisRequest newCurrentRedisLocationRequest = new NewCurrentLocationRedisRequest();
        newCurrentRedisLocationRequest.setUserId(newUserRequest.getUserId());
        currentLocationRedisService.setCurrentLocation(newCurrentRedisLocationRequest);
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


    @Override
    @Transactional
    public LoginResponse getMeetingInfo(User user, LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();

        if(user == null) { // id가 존재하지 않는 경우
            loginResponse.setLoginResult("false");
            loginResponse.setErrorMsg("존재하지 않는 id 입니다.");
            return loginResponse;
        }
        boolean check = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()); // 비밀번호 checking

        if(!check) { // password not matching
            loginResponse.setErrorMsg("비밀번호가 일치하지 않습니다.");
            loginResponse.setLoginResult("false");
        } else {
            loginResponse.setLoginResult("true");
        }
        loginResponse.setJwtToken(jwtTokenProvider.createToken(user.getUserId(), user.getRoles()));
        loginResponse.setUserId(user.getUserId());
        loginResponse.setName(user.getName());

        List<Meeting> meetings = meetingService.getMeetingList(user.getUserId()).getMeetingList();
        List<MeetingInfo> meetingInfoList = new ArrayList<>();

        for (Meeting meeting : meetings) {
            MeetingInfo meetingInfo = new MeetingInfo();
            meetingInfo.setMeetingId(Integer.toString(meeting.getId())); // set meeting id
            meetingInfo.setMeetingName(meeting.getName()); // set meeting name
            meetingInfo.setAppointmentTime(meeting.getAppointmentTime()); // set meeting appointment time
            meetingInfo.setDestinationLocation(meeting.getDestinationLocation()); // set meeting destination location
            Optional<MeetingUser> meetingUserOptional = meetingUserRepository.findById(meeting.getId()); // get meeting user list
            List<MeetingUser> meetingUserList =  meetingUserOptional.isPresent()
                    ? Collections.singletonList(meetingUserOptional.get())
                    : Collections.emptyList();
            meetingInfo.setUserList(meetingUserList.stream().map(meetingUser -> meetingUser.getUser().getUsername()).collect(Collectors.toList())); // set meeting participant list
            MeetingUser meetingUser = meetingUserRepository.findByUserIdAndMeetingId(user.getId(), meeting.getId());
            meetingInfo.setLocationSharingState(meetingUser.getLocationSharingState()); // set user's location sharing state
            meetingInfo.setMeetingUserType(meetingUser.getMeetingUserType()); // set meeting user type
            meetingInfo.setState(meeting.getState()); // set meeting type
            meetingInfoList.add(meetingInfo);
        }

        loginResponse.setMeetingInfoList(meetingInfoList);
        return loginResponse;
    }

}
