package com.projectMeeting4U.main.springboot.Meeting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UpdateMeetingRequset {
    @NotNull
    private String meetingId;

    @Column(name = "appointment_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointmentTime;

    private String destinationLocation;

    private String meetingName;

    public String getMeetingId() { return meetingId; }

    public String getDestinationLocation() { return destinationLocation; }

    public String getMeetingName() { return meetingName; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }

}
