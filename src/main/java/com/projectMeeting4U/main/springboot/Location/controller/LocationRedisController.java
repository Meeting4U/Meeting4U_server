package com.projectMeeting4U.main.springboot.Location.controller;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRedisRepository;
import com.projectMeeting4U.main.springboot.Location.repository.CurrentLocationRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LocationRedisController {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    CurrentLocationRedisRepository currentLocationRedisRepository;

    public LocationRedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation(value = "redis key 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT TOKEN", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/location")
    public ResponseEntity<?> addRedisKey(@ApiParam(value = "userId", defaultValue = "") @RequestParam String key,
                                         @ApiParam(value = "longitude", defaultValue = "") @RequestParam String longitude,
                                         @ApiParam(value = "latitude", defaultValue = "") @RequestParam String latitude) {

        HashOperations<String, String, String> hop = redisTemplate.opsForHash();
        HashMap<String, String> locationMap = new HashMap<>();
        locationMap.put("longitude", longitude);
        locationMap.put("latitude", latitude);

        hop.putAll(key, locationMap);

        return null;
    }

    @ApiOperation(value = "redis value update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT TOKEN", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping("/location/{key}")
    public Optional<CurrentLocationRedis> updateLocationRedis(@ApiParam(value = "key", defaultValue = "") @PathVariable String key,
                                                              @ApiParam(value = "longitude", defaultValue = "") @RequestParam String longitude,
                                                              @ApiParam(value = "latitude", defaultValue = "") @RequestParam String latitude) {

        CurrentLocationRedis currentLocationRedis = new CurrentLocationRedis(key, longitude, latitude);
        currentLocationRedisRepository.save(currentLocationRedis);

        Optional<CurrentLocationRedis> updateLocationRedis = currentLocationRedisRepository.findById(key);

        return updateLocationRedis;
    }


    @ApiOperation(value = "redis value 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT TOKEN", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/location/{key}")
    public Optional<CurrentLocationRedis> getRedisKey(@ApiParam(value = "key", defaultValue = "") @PathVariable String key) {

        Optional<CurrentLocationRedis> currentLocationRedis = currentLocationRedisRepository.findById(key);

        return currentLocationRedis;
    }

}
