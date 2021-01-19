package com.projectMeeting4U.main.springboot.Meeting.entity;

import com.projectMeeting4U.main.springboot.Location.entity.DepartLocation;
import com.projectMeeting4U.main.springboot.User.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "meeting_user")
public class MeetingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private MeetingUserType meetingUserType;

    @Column(name = "location_sharing_state")
    @Enumerated(EnumType.STRING)
    private LocationSharingState locationSharingState;

    @OneToOne
    @JoinColumn(name = "depart_loc_id")
    private DepartLocation departLocation;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    MeetingUser() {}

    public MeetingUser(
            Meeting meeting,
            User user,
            DepartLocation departLocation,
            LocationSharingState locationSharingState,
            MeetingUserType meetingUserType
    ) {
        this.meeting = meeting;
        this.user = user;
        this.departLocation = departLocation;
        this.locationSharingState = locationSharingState;
        this.meetingUserType = meetingUserType;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() { return id; }

    public MeetingUserType getMeetingUserType() { return meetingUserType; }

    public LocationSharingState getLocationSharingState() { return locationSharingState; }

    public User getUser() { return user; }

    public DepartLocation getDepartLocation() { return departLocation; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

}
