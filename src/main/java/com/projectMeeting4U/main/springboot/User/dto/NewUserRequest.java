package com.projectMeeting4U.main.springboot.User.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class NewUserRequest {
    @NotNull
    @Column(name = "user_id")
    private String userId;

    @NotNull
    private String name;

    @Column(name = "home_address")
    private String homeAddress;

    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getHomeAddress() { return homeAddress; }
}
