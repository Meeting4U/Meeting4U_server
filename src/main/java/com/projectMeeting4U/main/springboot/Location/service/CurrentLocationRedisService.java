package com.projectMeeting4U.main.springboot.Location.service;

import com.projectMeeting4U.main.springboot.Location.dto.CurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRedisRepository;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.Meeting.repository.MeetingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentLocationRedisService implements CurrentLocationRedisInterface {

    @Autowired
    private CurrentLocationRedisRepository currentLocationRedisRepository;

    @Autowired
    private MeetingUserRepository meetingUserRepository;


    @Override
    public NewCurrentLocationRedisResponse setCurrentLocation(NewCurrentLocationRedisRequest newCurrentRedisLocationRequest) {
        NewCurrentLocationRedisResponse newCurrentRedisLocationResponse = new NewCurrentLocationRedisResponse();

        CurrentLocationRedis currentLocationRedis = new CurrentLocationRedis(
                newCurrentRedisLocationRequest.getUserId(),
                newCurrentRedisLocationRequest.getLatitude(),
                newCurrentRedisLocationRequest.getLongitude());

        try {
            currentLocationRedisRepository.save(currentLocationRedis);
            newCurrentRedisLocationResponse.setResult("true");
            newCurrentRedisLocationResponse.setLatitude(newCurrentRedisLocationRequest.getLatitude());
            newCurrentRedisLocationResponse.setLongitude(newCurrentRedisLocationRequest.getLongitude());
        } catch (Exception e) {
            newCurrentRedisLocationResponse.setResult("false");
        }

        return newCurrentRedisLocationResponse;
    }

    @Override
    public CurrentLocationRedisResponse getCurrentLocationList(Integer meetingId) {
        CurrentLocationRedisResponse currentLocationRedisResponse = new CurrentLocationRedisResponse();

        List<MeetingUser> meetingUserList = meetingUserRepository.findByMeetingId(meetingId);

        List<String> userIdList = meetingUserList.stream().map(meetingUser -> meetingUser.getUser().getUsername()).collect(Collectors.toList());

        if(userIdList == null) {
            currentLocationRedisResponse.setResult("false");
            return currentLocationRedisResponse;
        }

        System.out.println(userIdList);
        List<CurrentLocationRedis> currentLocationRedisList = userIdList.stream().map(userId -> currentLocationRedisRepository.findById(userId).get()).collect(Collectors.toList());

        currentLocationRedisResponse.setResult("true");
        currentLocationRedisResponse.setCurrentLocationRedisList(currentLocationRedisList);

        return currentLocationRedisResponse;
    }
}
