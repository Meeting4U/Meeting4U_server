package com.projectMeeting4U.main.springboot.Meeting.service;

import com.projectMeeting4U.main.springboot.Exception.ResourceNotFoundException;
import com.projectMeeting4U.main.springboot.Meeting.dto.MeetingResponse;
import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingRepository;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingUserRepository;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeetingService implements MeetingInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetingUserRepository meetingUserRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    @Transactional
    public MeetingResponse getMeetingList(String userId) {
        MeetingResponse meetingResponse = new MeetingResponse();
        List<Meeting> meetingList = new ArrayList<Meeting>();

        Optional<User> optionalUser = userRepository.findById(userRepository.findByUserId(userId).getId());
        if(!optionalUser.isPresent()) {
            meetingResponse.setResult("false");
            return meetingResponse;
        }
        else {
            meetingResponse.setResult("true");
        }
        User user = optionalUser.get();
        List<MeetingUser> meetingUsers = meetingUserRepository.findByUser(user);

        meetingList = meetingUsers.stream().map(meetingUser -> meetingUser.getMeeting()).collect(Collectors.toList());

        meetingResponse.setMeetingList(meetingList);
        return meetingResponse;
    }
}
