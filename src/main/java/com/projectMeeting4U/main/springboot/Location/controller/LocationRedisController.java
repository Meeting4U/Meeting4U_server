package com.projectMeeting4U.main.springboot.Location.controller;

import com.projectMeeting4U.main.springboot.Location.dto.CurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisRequest;
import com.projectMeeting4U.main.springboot.Location.dto.NewCurrentLocationRedisResponse;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRedisRepository;
import com.projectMeeting4U.main.springboot.Location.service.CurrentLocationRedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LocationRedisController {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    CurrentLocationRedisRepository currentLocationRedisRepository;

    @Autowired
    private CurrentLocationRedisService currentLocationRedisService;

    public LocationRedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation(value = "redis key 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT TOKEN", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/location")
    public NewCurrentLocationRedisResponse setCurrentLocation(@ApiParam(value = "newCurrentLocation", defaultValue = "") @Valid @RequestBody NewCurrentLocationRedisRequest newCurrentRedisLocationRequest) {
        NewCurrentLocationRedisResponse newCurrentRedisLocationResponse = currentLocationRedisService.setCurrentLocation(newCurrentRedisLocationRequest);

        return newCurrentRedisLocationResponse;
    }

    @ApiOperation(value = "get all current location")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT TOKEN", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/location/{meetingId}")
    public CurrentLocationRedisResponse getCurrentLocationList(@ApiParam(value = "meetingId", defaultValue = "") @PathVariable Integer meetingId) {
        CurrentLocationRedisResponse currentLocationRedisResponse = currentLocationRedisService.getCurrentLocationList(meetingId);
        return currentLocationRedisResponse;
    }

//    @ApiOperation(value = "redis value 조회")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT TOKEN", required = true, dataType = "String", paramType = "header")
//    })
//    @GetMapping("/location/{key}")
//    public Optional<CurrentLocationRedis> getCurrentLocation(@ApiParam(value = "key", defaultValue = "") @PathVariable String key) {
//
//        Optional<CurrentLocationRedis> currentLocationRedis = currentLocationRedisRepository.findById(key);
//
//        return currentLocationRedis;
//    }

}
