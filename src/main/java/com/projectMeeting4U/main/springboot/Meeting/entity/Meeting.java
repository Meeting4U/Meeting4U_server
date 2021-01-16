package com.projectMeeting4U.main.springboot.Meeting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projectMeeting4U.main.springboot.Location.entity.DestinationLocation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "destination_loc_id")
    private DestinationLocation destinationLocation;

    @JsonFormat(pattern = "YYYY-mm-dd HH:mm:ss")
    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private MeetingState state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "meeting")
    private Set<MeetingUser> meetingUsers = new HashSet<>();

    public Meeting() {};

    public Meeting(
            String name,
            DestinationLocation desLoc,
            LocalDateTime appointmentTime,
            MeetingState meetingState
    ) {
        this.name = name;
        this.destinationLocation = desLoc;
        this.appointmentTime = appointmentTime;
        this.state = meetingState;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public DestinationLocation getDestinationLocation() { return  destinationLocation; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }

    public MeetingState getState() { return state; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public Set<MeetingUser> getMeetingUsers() { return meetingUsers; }
}
