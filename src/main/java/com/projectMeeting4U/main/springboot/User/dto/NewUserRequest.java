package com.projectMeeting4U.main.springboot.User.dto;

import lombok.Builder;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class NewUserRequest {
    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "home_address")
    private String homeAddress;

    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getHomeAddress() { return homeAddress; }
}
