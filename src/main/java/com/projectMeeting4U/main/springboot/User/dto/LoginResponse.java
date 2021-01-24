package com.projectMeeting4U.main.springboot.User.dto;

import javax.validation.constraints.NotNull;

public class LoginResponse {
    @NotNull
    private String loginResult;

    private String jwtToken;

    public String getLoginResult() { return loginResult; }

    public String getJwtToken() { return jwtToken; }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
