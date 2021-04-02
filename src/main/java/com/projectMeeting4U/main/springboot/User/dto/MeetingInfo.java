package com.projectMeeting4U.main.springboot.User.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectMeeting4U.main.springboot.Location.entity.DestinationLocation;
import com.projectMeeting4U.main.springboot.Meeting.entity.LocationSharingState;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingState;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUserType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingInfo {
    @NotNull
    private String meetingId;

    private String meetingName;

    @Enumerated(EnumType.STRING)
    private MeetingUserType meetingUserType;

    @JsonFormat(pattern = "YYYY-mm-dd HH:mm:ss")
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private MeetingState state;

    @Enumerated(EnumType.STRING)
    private LocationSharingState locationSharingState;

    private List<String> userList = new ArrayList<>();

    private String latitude;

    private String longitude;

    public String getMeetingId() { return meetingId; }

    public String getMeetingName() { return meetingName; }

    public MeetingUserType getMeetingUserType() { return meetingUserType; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }

    public MeetingState getState() { return state; }

    public LocationSharingState getLocationSharingState() { return locationSharingState; }

    public List<String> getUserList() { return userList; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public void setMeetingId(String meetingId) { this.meetingId = meetingId; }

    public void setMeetingName(String meetingName) { this.meetingName = meetingName; }

    public void setMeetingUserType(MeetingUserType meetingUserType) { this.meetingUserType = meetingUserType; }

    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public void setState(MeetingState state) { this.state = state; }

    public void setLocationSharingState(LocationSharingState locationSharingState) { this.locationSharingState = locationSharingState; }

    public void setUserList(List<String> userList) { this.userList = userList; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

}
