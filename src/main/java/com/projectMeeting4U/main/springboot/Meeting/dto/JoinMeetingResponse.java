package com.projectMeeting4U.main.springboot.Meeting.dto;

import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import com.projectMeeting4U.main.springboot.User.entity.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class JoinMeetingResponse {
    @NotNull
    private String result;

    private List<User> userList = new ArrayList<>();

    public String getResult() { return result; }

    public List<User> getUserList() { return userList; }

    public void setResult(String result) { this.result = result; }

    public void setUserList(List<User> userList) { this.userList = userList; }
}
