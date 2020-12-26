package com.projectMeeting4U.main.springboot.Location.repository;

import com.projectMeeting4U.main.springboot.Location.entity.DestinationLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestLocationRepository extends JpaRepository<DestinationLocation, Integer> {
}
