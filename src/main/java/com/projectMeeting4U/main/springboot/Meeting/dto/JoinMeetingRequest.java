package com.projectMeeting4U.main.springboot.Meeting.dto;

import com.projectMeeting4U.main.springboot.Location.entity.DepartLocation;

import javax.validation.constraints.NotNull;

public class JoinMeetingRequest {
    @NotNull
    private String userId;

    private String meetingId;

    private DepartLocation departLocation;

    public String getUserId() { return userId; }

    public String getMeetingId() { return meetingId; }

    public DepartLocation getDepartLocation() { return departLocation; }

    public void setUserId(String userId){ this.userId = userId; }

    public void setMeetingId(String meetingId) { this.meetingId = meetingId; }

    public void setDepartLocation(DepartLocation departLocation) { this.departLocation = departLocation; }
}
