package com.projectMeeting4U.main.springboot.User.dto;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;

import javax.validation.constraints.NotNull;

public class UserResponse{
    @NotNull
    private String result;

    private String userId;

    private String name;

    private String email;

    private String phoneNumber;

    private String homeAddress;

    private CurrentLocation currentLocation;

    private String createdAt;

    private String updatedAt;

    public String getResult() { return result; }

    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getHomeAddress() { return homeAddress; }

    public CurrentLocation getCurrentLocation() { return currentLocation; }

    public String getCreatedAt() { return createdAt; }

    public String getUpdatedAt() { return updatedAt; }

    public void setResult(String result) { this.result = result; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }

    public void setCurrentLocation(CurrentLocation currentLocation) { this.currentLocation = currentLocation; }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

}
