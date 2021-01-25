package com.projectMeeting4U.main.springboot.Location.service;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentLocationRedisService implements CurrentLocationRedisInterface {

    @Autowired
    private CurrentLocationRedisRepository currentLocationRedisRepository;

    @Override
    public CurrentLocationRedis setCurrentLocation(String userid, String latitude, String longitude) {
        CurrentLocationRedis currentLocationRedis = new CurrentLocationRedis(userid, latitude, longitude);

        try {
            currentLocationRedisRepository.save(currentLocationRedis);
        } catch (Exception e) {
            return null;
        }

        return currentLocationRedis;
    }
}
