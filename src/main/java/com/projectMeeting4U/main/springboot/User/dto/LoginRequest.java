package com.projectMeeting4U.main.springboot.User.dto;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    private String userId;

    @NotNull
    private String password;

    public String getUserId() { return userId; }

    public String getPassword() { return password; }

}
