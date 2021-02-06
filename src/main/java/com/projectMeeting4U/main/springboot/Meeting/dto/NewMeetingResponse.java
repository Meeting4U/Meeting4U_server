package com.projectMeeting4U.main.springboot.Meeting.dto;

import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;

import javax.validation.constraints.NotNull;

public class NewMeetingResponse {
    @NotNull
    private String result;

    private Meeting meeting;

    public String getResult() { return result; }

    public Meeting getMeeting() { return meeting; }

    public void setResult(String result) { this.result = result; }

    public void setMeeting(Meeting meeting) { this.meeting = meeting; }


}
