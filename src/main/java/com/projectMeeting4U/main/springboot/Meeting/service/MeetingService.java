package com.projectMeeting4U.main.springboot.Meeting.service;

import com.projectMeeting4U.main.springboot.Exception.ResourceNotFoundException;
import com.projectMeeting4U.main.springboot.Location.controller.LocationController;
import com.projectMeeting4U.main.springboot.Location.dto.NewDepartLocationRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewDestLocationRequest;
import com.projectMeeting4U.main.springboot.Location.entity.DepartLocation;
import com.projectMeeting4U.main.springboot.Location.entity.DestinationLocation;
import com.projectMeeting4U.main.springboot.Location.repository.DestLocationRepository;
import com.projectMeeting4U.main.springboot.Meeting.dto.*;
import com.projectMeeting4U.main.springboot.Meeting.entity.*;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingRepository;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingUserRepository;
import com.projectMeeting4U.main.springboot.User.entity.User;
import com.projectMeeting4U.main.springboot.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    @Autowired
    private DestLocationRepository destLocationRepository;

    @Autowired
    private LocationController locationController;

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

    @Override
    public NewMeetingResponse createMeeting(NewMeetingRequest newMeetingRequest) {
        NewMeetingResponse newMeetingResponse = new NewMeetingResponse();

        NewDestLocationRequest newDestLocationRequest = new NewDestLocationRequest((newMeetingRequest.getDestinationAddress()));
        NewDepartLocationRequest newDepartLocationRequest = new NewDepartLocationRequest();
        DestinationLocation newDestination = locationController.newDestLocation(newDestLocationRequest); // save to DB
        DepartLocation newDepartLocation = locationController.newDepartLocation(newDepartLocationRequest); // save to DB

        Meeting meeting = new Meeting(
                newMeetingRequest.getName(),
                newDestination,
                newMeetingRequest.getAppointmentTime(),
                MeetingState.MEETING_READY
        );

        meetingRepository.save(meeting);

        User hostUser = userRepository.findById(userRepository.findByUserId(newMeetingRequest.getUserId()).getId()).get();
        if(hostUser == null) {
            newMeetingResponse.setResult("false");
            return newMeetingResponse;
        }

        MeetingUser meetingUser = new MeetingUser(
                meeting,
                hostUser,
                newDepartLocation,
                LocationSharingState.PRIVATE,
                MeetingUserType.HOST
        );
        meetingUserRepository.save(meetingUser);
        newMeetingResponse.setResult("true");
        newMeetingResponse.setMeeting(meeting);

        return newMeetingResponse;
    }

    @Override
    public JoinMeetingResponse joinMeeting(JoinMeetingRequest joinMeetingRequest) {
        JoinMeetingResponse joinMeetingResponse = new JoinMeetingResponse();

        Meeting meeting = meetingRepository.findById(Integer.valueOf(joinMeetingRequest.getMeetingId())).get();
        User user = userRepository.findByUserId(joinMeetingRequest.getUserId());

        MeetingUser meetingUser = new MeetingUser(
                meeting,
                user,
                joinMeetingRequest.getDepartLocation(),
                LocationSharingState.PRIVATE,
                MeetingUserType.PARTICIPANT
        );

        try{
            meetingUserRepository.save(meetingUser);
        } catch (Exception e) {
            joinMeetingResponse.setResult("false");
            return joinMeetingResponse;
        }

        List<MeetingUser> meetingUserList = meetingUserRepository.findByMeetingId(Integer.valueOf(joinMeetingRequest.getMeetingId()));
        List<User> userList = meetingUserList.stream().map(mu -> mu.getUser()).collect(Collectors.toList());

        joinMeetingResponse.setResult("true");
        joinMeetingResponse.setUserList(userList);

        return joinMeetingResponse;
    }

    @Override
    public UpdateMeetingResponse updateMeeting(UpdateMeetingRequset updateMeetingRequset) {
        UpdateMeetingResponse updateMeetingResponse = new UpdateMeetingResponse();
        Meeting meeting = meetingRepository.findById(Integer.valueOf(updateMeetingRequset.getMeetingId())).get();
        String meetingName = meeting.getName();
        String destinationLocationStr = meeting.getDestinationLocation().getAddress();
        LocalDateTime appointmentTime = meeting.getAppointmentTime();

        if(updateMeetingRequset.getAppointmentTime() != null)
            appointmentTime = updateMeetingRequset.getAppointmentTime();

        if(updateMeetingRequset.getDestinationLocation() != null)
            destinationLocationStr = updateMeetingRequset.getDestinationLocation();

        if(updateMeetingRequset.getMeetingName() != null)
            meetingName = updateMeetingRequset.getMeetingName();

        DestinationLocation destinationLocation = destLocationRepository.findById(Integer.valueOf(meeting.getDestinationLocation().getId())).get();

        destinationLocation.changeDestination(destinationLocationStr);
        destLocationRepository.save(destinationLocation);

        meeting.changeMeeting(meetingName, destinationLocation, appointmentTime);
        meetingRepository.save(meeting);

        updateMeetingResponse.setResult("true");
        updateMeetingResponse.setMeeting(meetingRepository.findById(Integer.valueOf(updateMeetingRequset.getMeetingId())).get());


        return updateMeetingResponse;
    }
}
