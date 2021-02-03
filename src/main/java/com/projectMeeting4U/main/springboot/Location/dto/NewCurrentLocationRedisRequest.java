package com.projectMeeting4U.main.springboot.Location.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

public class NewCurrentLocationRedisRequest {
    @NotNull
    private String userId;

    private String latitude;

    private String longitude;

    public String getUserId() { return userId; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }


}
