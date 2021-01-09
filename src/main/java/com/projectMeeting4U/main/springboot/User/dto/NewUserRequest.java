package com.projectMeeting4U.main.springboot.User.dto;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getHomeAddress() { return homeAddress; }

    public List<String> getRoles() { return roles; }
}
