package com.projectMeeting4U.main.springboot.Location.dto;

import javax.validation.constraints.NotNull;

public class NewCurrentLocationRedisResponse {
    @NotNull
    private String result;

    private String latitude;

    private String longitude;

    public String getResult() { return result; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public void setResult(String result) { this.result = result; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }
}
