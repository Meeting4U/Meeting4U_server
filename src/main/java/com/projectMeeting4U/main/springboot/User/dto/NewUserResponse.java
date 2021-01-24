package com.projectMeeting4U.main.springboot.User.dto;

import javax.validation.constraints.NotNull;

public class NewUserResponse {
    @NotNull
    private String result;

    public NewUserResponse(String result) {
        this.result = result;
    }

    public String getResult() { return result; }

}
