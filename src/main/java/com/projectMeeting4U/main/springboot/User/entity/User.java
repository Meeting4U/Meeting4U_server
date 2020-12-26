package com.projectMeeting4U.main.springboot.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private String userId;

    private String name;

    @Column(name = "home_address")
    private String homeAddress;

    @OneToOne
    @JoinColumn(name = "current_loc_id")
    private CurrentLocation currentLocation;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private Set<MeetingUser> meetingUsers = new HashSet<>();

    public User() {}

    public User(
            String userId,
            String name,
            String homeAddress,
            CurrentLocation currentLocation
    ) {
        this.userId = userId;
        this.name = name;
        this.homeAddress = homeAddress;
        this.currentLocation = currentLocation;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getHomeAddress() { return homeAddress; }

    public CurrentLocation getCurrentLocation() { return currentLocation; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt;}

    @JsonIgnore
    public Set<MeetingUser> getMeetingUsers() { return meetingUsers; }
}
