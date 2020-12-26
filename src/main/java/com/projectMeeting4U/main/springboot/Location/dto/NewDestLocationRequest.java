package com.projectMeeting4U.main.springboot.Location.dto;

public class NewDestLocationRequest {
    private String address;

    public NewDestLocationRequest() {}

    public NewDestLocationRequest(String address) {
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() { return address; }
}
