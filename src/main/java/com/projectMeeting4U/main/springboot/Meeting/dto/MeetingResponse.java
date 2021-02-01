package com.projectMeeting4U.main.springboot.Meeting.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectMeeting4U.main.springboot.Meeting.entity.Meeting;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MeetingResponse {
    @NotNull
    private String result;

    private List<Meeting> meetingList = new ArrayList<>();

    public String getResult() { return result; }

    public List<Meeting> getMeetingList() { return meetingList; }

    public void setResult(String result) { this.result = result; }

    public void setMeetingList(List<Meeting> meetingList) { this.meetingList = meetingList; }
}
