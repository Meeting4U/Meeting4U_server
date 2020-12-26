package com.projectMeeting4U.main.springboot.Location.repository;

import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentLocationRepository extends JpaRepository<CurrentLocation, Integer> {
}
