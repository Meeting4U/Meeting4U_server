package com.projectMeeting4U.main.springboot.Location.service;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;

public interface CurrentLocationRedisInterface {
    public CurrentLocationRedis setCurrentLocation(String userid, String latitude, String longitude);
}
