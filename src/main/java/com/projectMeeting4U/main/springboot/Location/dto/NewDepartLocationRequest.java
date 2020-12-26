package com.projectMeeting4U.main.springboot.Location.dto;

public class NewDepartLocationRequest {
    private String address;

    public NewDepartLocationRequest() {}

    public NewDepartLocationRequest(String address) {
        this.address = address;
    }

    public String getAddress() { return address; }
}
