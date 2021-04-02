package com.projectMeeting4U.main.springboot.Location.entity;

import com.projectMeeting4U.main.springboot.Location.dto.GeocoderResponse;
import com.projectMeeting4U.main.springboot.Location.service.CurrentLocationRedisService;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "destination_location")
public class DestinationLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String latitude;

    private String longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public DestinationLocation() {}

    public DestinationLocation(String address) {
        this.address = address;
         // address to latutude, longitude
        CurrentLocationRedisService currentLocationRedisService = new CurrentLocationRedisService();
        GeocoderResponse geocoderResponse = currentLocationRedisService.getLocationFromGeocoder(address);
        if(geocoderResponse.getResult() != "false") {
            this.latitude = geocoderResponse.getLatitude();
            this.longitude = geocoderResponse.getLongitude();
        }

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() { return id; }

    public String getAddress() { return address; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void changeDestination(String address) {
        this.address = address;
        // address to latutude, longitude
        CurrentLocationRedisService currentLocationRedisService = new CurrentLocationRedisService();
        GeocoderResponse geocoderResponse = currentLocationRedisService.getLocationFromGeocoder(address);
        if(geocoderResponse.getResult() != "false") {
            this.latitude = geocoderResponse.getLatitude();
            this.longitude = geocoderResponse.getLongitude();
        }

        this.updatedAt = LocalDateTime.now();
    }

}
