package com.projectMeeting4U.main.springboot.Location.entity;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@RedisHash("currentLocationRedis")
public class CurrentLocationRedis {
    @Id
    private String id;
    private String longitude;
    private String latitude;
    private LocalDateTime updatedAt;

    public CurrentLocationRedis(String id, String longitude, String latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.updatedAt = LocalDateTime.now();;
    }

    public String getId() { return id; }

    public String getLongitude() { return longitude; }

    public String getLatitude() { return latitude; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(String id) { this.id = id; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }


}
