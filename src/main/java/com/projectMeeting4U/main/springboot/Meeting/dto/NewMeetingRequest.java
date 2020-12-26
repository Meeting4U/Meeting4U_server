package com.projectMeeting4U.main.springboot.Meeting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NewMeetingRequest {
    @NotNull
    private int userId;

    @NotNull
    private String name;

    private String destinationAddress;

    @Column(name = "appointment_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appointmentTime;

    public int getUserId() { return userId; }

    public String getName() { return name; }

    public String getDestinationAddress() { return  destinationAddress; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
}
