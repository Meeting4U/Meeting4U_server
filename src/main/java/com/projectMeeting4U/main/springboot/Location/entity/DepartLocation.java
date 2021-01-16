package com.projectMeeting4U.main.springboot.Location.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "depart_location")
public class DepartLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String latitude;

    private String longitude;

    @Column(name = "depart_time")
    private LocalDateTime departTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public DepartLocation() {}

    public DepartLocation(String address) {
        this.address = address;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
