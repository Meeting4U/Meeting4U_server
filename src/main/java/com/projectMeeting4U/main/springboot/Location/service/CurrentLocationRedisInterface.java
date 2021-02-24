package com.projectMeeting4U.main.springboot.Location.service;

import com.projectMeeting4U.main.springboot.Location.dto.CurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisResponse;

public interface CurrentLocationRedisInterface {
    public NewCurrentLocationRedisResponse setCurrentLocation(NewCurrentLocationRedisRequest newCurrentRedisLocationRequest);
    public CurrentLocationRedisResponse getCurrentLocationList(Integer meetingId);
}
