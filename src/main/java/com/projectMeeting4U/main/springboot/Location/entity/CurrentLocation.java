package com.projectMeeting4U.main.springboot.Location.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_location")
public class CurrentLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String latitude;

    private String longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public CurrentLocation() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
