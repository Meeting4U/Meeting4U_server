package com.projectMeeting4U.main.springboot.Location.dto;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocationRedis;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CurrentLocationRedisResponse {
    @NotNull
    private String result;

    private List<CurrentLocationRedis> currentLocationRedisList = new ArrayList<>();

    public String getResult() { return result; }

    public List<CurrentLocationRedis> getCurrentLocationRedisList() { return currentLocationRedisList; }

    public void setResult(String result) { this.result = result; }

    public void setCurrentLocationRedisList(List<CurrentLocationRedis> currentLocationRedisList) { this.currentLocationRedisList = currentLocationRedisList; }
}
